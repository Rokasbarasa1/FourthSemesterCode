#include <stdio.h>

#include "FreeRTOS.h"
#include "task.h"
#include "message_buffer.h"
#include "semphr.h"
#include "event_groups.h"

#include "AppController.h"
#include "definitions.h"
#include "WaterLevelSensor.h"
#include "WaterTemperatureSensor.h"
#include "MessagePayload.h"
#include "Transmitter.h"

//The task of the AppController and associated task is to assemble and manage the entire application. It is
//thus this that must start a measurement in WaterLevelSensor and retrieve the result of the measurements
//from the two sensorsand pack these in a MessagePayload which is then sent via payloadMessageBuffer.

static waterLevelSensor_t waterLevelSensor;
static waterTemperatureSensor_t waterTemperatureSensor;
static MessageBufferHandle_t payloadMessagebuffer;
static EventGroupHandle_t eventReady;
static SemaphoreHandle_t printMutex;

//Inline method contents are place on call spot (In the task). Removes the need for overhead needed for methods.
inline void appController_collectMeasurements() {
	waterLevelSensor_measure(waterLevelSensor); 	//Starts a measurement in water level sensor

	EventBits_t data_bits = xEventGroupWaitBits(  	//Wait for bits to be set.
		eventReady, //The event group in which the bits are.
		DEF_BIT_DATA_READY_ALL, //Waiting for both temperature and level to be ready. Bitwise value
		pdTRUE, //Clear ready bits after waiting. 
		pdTRUE, //Make sure that all bits are ready. Does not apply because i am passing a single definition of "DEF_BIT_DATA_READY_ALL".
		// if i had 2 bit values seperated by | then it would apply.
		pdMS_TO_TICKS(120)//Max amount of time i can wait. 120 ms because the measure of water level takes 100 ms. If it takes more than 120 it wont wait.
	);
	

	if (data_bits == 3) { 	//Checks if bits are equal to 0011 which is 3 in decimal.
		uint16_t waterLevel = waterLevelSensor_getLastWaterLevel(waterLevelSensor);
		int8_t waterTemperature = waterTemperatureSensor_getLastWaterTemperature(waterTemperatureSensor);

		//A mutex makes sure things arent written at the same time to the console.
		if (xSemaphoreTake(printMutex, portMAX_DELAY) == pdTRUE) {
			printf("The values are: %d %d\n", waterLevel, waterTemperature);
			xSemaphoreGive(printMutex);
		}
		//Turns the measurements into a payload object. Which holds lenght and bytes
		message_payload_t payload = messagePayload_create(waterLevel, waterTemperature);
		//Sends address of payload to message buffer from which the transmiter will receive. Will wait max 200 ms to put something in
		xMessageBufferSend(payloadMessagebuffer, &payload, sizeof(message_payload_t), pdMS_TO_TICKS(200));
	}
	else {
		if (xSemaphoreTake(printMutex, portMAX_DELAY) == pdTRUE) {
			printf("Error: data bits not set");
			xSemaphoreGive(printMutex);
		}
	}
}

//A task that is specified to run approximetely once per second.
static void appController_task(void* pvParameters) {
	//It is desired that the application transmits once per second.
	TickType_t xLastWakeTime = xTaskGetTickCount();
	const TickType_t xFrequency = DEF_DELAY_TASK_APP_CONTROLER; // Delay can be found in definitions.h

	for (;;){
		//A more acurate delay. Will check how long the task took to accomplish and will adjust the delay to
		//still be in the boundries of approximately one second
		vTaskDelayUntil(&xLastWakeTime, xFrequency);
		//Called to collect measurements and make payload
		appController_collectMeasurements();
	}
}

//A create method to initialize the object.
//The object is static therefore it does not need to return a reference of itself.
//Manages the application and therefore creates all the other classes
void appController_create() {
	eventReady = xEventGroupCreate(); 	//Event group to know when measurements are ready.

	waterLevelSensor = waterLevelSensor_create(eventReady); 	//Two sensors that will serve the events
	waterTemperatureSensor = waterTemperatureSensor_create(eventReady);

	//A message buffer to synchronize the transmiter and the app controller.
	//The + sizeof(size_t) is for storing the lenght of the message.
	payloadMessagebuffer = xMessageBufferCreate(sizeof(message_payload_t) + sizeof(size_t));

	//Print mutex that controlls priority of access for printing to console.
	printMutex = xSemaphoreCreateMutex();

	//Transmiter static object that will print the payload to console.
	transmitter_create(payloadMessagebuffer, printMutex);

	//Task of the app controller. 
	//No values passed and no handler needed as there is no need to interfere with it.
	xTaskCreate(
		appController_task, //method which to run.
		"app controller task", //Name of thread for humans.
		TASK_APP_CONTROLER_TASK_STACK, //Stack size
		NULL, // Passed parameter
		TASK_APP_CONTROLER_TASK_PRIORITY, //Task priority lower is higher
		NULL // handler or as i would call a reference to the task.
	);
}