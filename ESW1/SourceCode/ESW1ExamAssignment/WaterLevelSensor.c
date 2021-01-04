#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>

#include "FreeRTOS.h"
#include "task.h"
#include "semphr.h"
#include "event_groups.h"

#include "definitions.h"
#include "WaterLevelSensor.h"
#include "WaterTemperatureSensor.h"
#include "MessagePayload.h"

//WaterTemperatureSensor and associated task: Has the task of simulating a water level sensor that works by
//starting an imaginary measurement when the meassure() function is called, this is simulated to take
//approx. 100 ms.After which the measurement is ready.At this point, the task must set a bit in a FreeRTOS
//Event group to signal that the measurement is readyand can be retrieved by calling the
//getLasterWaterLevel() function. (The EventGroup is not shown in the class diagram).

typedef struct WaterLevelSensor {
    uint16_t waterLevel;
    boolean measureNow;
    SemaphoreHandle_t mutex;
    EventGroupHandle_t eventReady;
}WaterLevelSensor;

//I took example from my sep iot team. But i am still a bit confused by the effectiveness of this.
//As i understand a spemahore is supposed to provide priority to a higher access thread.
//Doing it this way seems to provide a risk of the second thread accessing this interfering with 
//the result of the first thread. 
uint16_t waterLevelSensor_getLastWaterLevel(waterLevelSensor_t self) {
    uint16_t value;
    if (xSemaphoreTake(self->mutex, portMAX_DELAY) == pdTRUE) {
        value = self->waterLevel;
        xSemaphoreGive(self->mutex);
    }
    return value;
}

//I atemped to make a seperate task that would perform this functionality
//and let the program move along. But ran into issues of a task method reaching 
//it's end and going into an infinite loop instead of just stopping or lettin me delete it. 
void waterLevelSensor_measure(waterLevelSensor_t self) {
    self->measureNow = TRUE;
}

//Sets level to a random number from 0 to 30. Called from the task.
inline void waterLevelSensor_measureLevel(waterLevelSensor_t self) {
    vTaskDelay(pdMS_TO_TICKS(1));
    if (self->measureNow) {
        self->measureNow = FALSE;
        if (xSemaphoreTake(self->mutex, portMAX_DELAY) == pdTRUE) {
            vTaskDelay(DEF_DELAY_TASK_WATER_LEVEL_MEASURE);
            srand(time(NULL));
            self->waterLevel = rand() % 30;
            xSemaphoreGive(self->mutex);
            xEventGroupSetBits(self->eventReady, DEF_BIT_DATA_READY_WATER_LEVEL);
        }
    }   
}

//This method is really only active when the app controller calls measure. 
//The measure method sets a boolean value of "measureNow" to true. Which lets this task measure the level.
//Tried to do a dynamicaly added task but it did not work, this is the alternative.
static void waterLevelSensor_task(void* pvParameters) {
    waterLevelSensor_t self = (waterLevelSensor_t)pvParameters;
    for (;;)
    {
        waterLevelSensor_measureLevel(self);
    }
}

//As an alternative to setting the level to -1 I set it to a value that should not be reached which is 30000
waterLevelSensor_t waterLevelSensor_create(EventGroupHandle_t event_group_data_ready) {
    waterLevelSensor_t newSensor = calloc(sizeof(WaterLevelSensor), 1);

    //Check if it was acctualy created.
    if (NULL == newSensor) {
        return NULL;
    }

    //Default value
    newSensor->waterLevel = 30000;
    newSensor->measureNow = false;
    newSensor->mutex = xSemaphoreCreateMutex();
    newSensor->eventReady = event_group_data_ready;

    xTaskCreate(
        waterLevelSensor_task,
        "Water temperature task",
        TASK_WATER_TEMPERATURE_TASK_STACK,
        newSensor,
        TASK_WATER_TEMPERATURE_TASK_PRIORITY,
        NULL
    );

    return newSensor;
}