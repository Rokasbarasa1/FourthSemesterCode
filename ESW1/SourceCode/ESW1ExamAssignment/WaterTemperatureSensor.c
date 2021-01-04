#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "FreeRTOS.h"
#include "task.h"
#include "semphr.h"
#include "event_groups.h"

#include "definitions.h"
#include "WaterLevelSensor.h"
#include "WaterTemperatureSensor.h"
#include "MessagePayload.h"

//WaterTemperatureSensor and associated task is simpler, it just needs to simulate that it takes a new
//temperature measurement every 75 ms.The latest of these measurements can be retrieved at any time
//with a call to the getLasteWaterTemperature() function.

//Because it is a object and not a static class, it has its own variables that can
//be accessed when a self object is passed to a method
typedef struct WaterTemperatureSensor {
    int8_t latestTemperature;
    SemaphoreHandle_t mutex;
    EventGroupHandle_t eventReady;
}WaterTemperatureSensor;

int8_t waterTemperatureSensor_getLastWaterTemperature(waterTemperatureSensor_t self) {
    int8_t value;
    if (xSemaphoreTake(self->mutex, portMAX_DELAY) == pdTRUE) {
        value = self->latestTemperature;
        xSemaphoreGive(self->mutex);
    }
    return value;
}

//Sets the temperature to a random value that is between 0 and 19
inline void waterTemperatureSensor_measure(waterTemperatureSensor_t self) {
    if (xSemaphoreTake(self->mutex, portMAX_DELAY) == pdTRUE) {
        srand(time(NULL));
        self->latestTemperature = rand() % 20;
        xSemaphoreGive(self->mutex);
        xEventGroupSetBits(self->eventReady, DEF_BIT_DATA_READY_WATER_TEMPERATURE);
    }   
}

//Measures the water temperature automaticaly by calling measure. Runs approximately every 75 ms
static void water_temperature_task(void* pvParameters) {
    TickType_t xLastWakeTime = xTaskGetTickCount();
    const TickType_t xFrequency = DEF_DELAY_TASK_WATER_TEMPERATURE;
    for (;;){
        vTaskDelayUntil(&xLastWakeTime, xFrequency);
        waterTemperatureSensor_measure((waterTemperatureSensor_t)pvParameters);
    }
}

//Returns a reference for the created object so that it can be specified when calling a method
//Because it is object that is not static it needs to be allocated in memory.
//The size of memory allocated is the size of the struct that holds the objects variables.
waterTemperatureSensor_t waterTemperatureSensor_create(EventGroupHandle_t event_group_data_ready) {
    waterTemperatureSensor_t newSensor = calloc(sizeof(WaterTemperatureSensor), 1);

    //Check if it was actually created.
    if (NULL == newSensor) {
        return NULL;
    }
    //Default value
    newSensor->latestTemperature = -1;
    newSensor->mutex = xSemaphoreCreateMutex();
    newSensor->eventReady = event_group_data_ready;

    xTaskCreate(
        water_temperature_task,		
        "Water temperature task",			
        TASK_WATER_TEMPERATURE_TASK_STACK,			
        newSensor,				
        TASK_WATER_TEMPERATURE_TASK_PRIORITY,	
        NULL
    );

    return newSensor;
}


