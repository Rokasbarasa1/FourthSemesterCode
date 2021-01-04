#include <stdio.h>

#include "FreeRTOS.h"
#include "task.h"
#include "semphr.h"
#include "message_buffer.h"

#include "MessagePayload.h"
#include "definitions.h"

//Transmitterand associated task must simulate that a set of measurements is sent out of the system every
//time a new data set is ready.A data set consists of a water temperatureand a water level, respectively.The
//sendMessage() function must be called internally in the task every time a data set is readyand packed in a
//MessagePayload.It is this function that should print this MessagePayload byte by byte in hex format to the
//console.

//Mutext to synchronize printing to console
static SemaphoreHandle_t printMutex;

//Task will wait until payload is received.
inline void transmitter_sendMessage(MessageBufferHandle_t payloadMessagebuffer) {
	message_payload_t payload;
	size_t xReceivedBytes = xMessageBufferReceive(payloadMessagebuffer, (void*)&payload, sizeof(message_payload_t), pdMS_TO_TICKS(portMAX_DELAY));

	if (xReceivedBytes > 0) {
		//Mutex makes sure only one thread does printing at a time.
		if (xSemaphoreTake(printMutex, portMAX_DELAY) == pdTRUE) {
			printf("The payload is: ");
			for (int i = 0; i < messagePayload_getLength(payload); i++) {
				printf("%x ", messagePayload_getByte(payload, i));
			}
			printf("\n");
			printf("\n");
			xSemaphoreGive(printMutex);
		}
		//Frees the object after use so that it doesnt fill memory
		free(payload);
		payload = NULL;
	}
}

//TransmitterTask will receive a pre-packaged payload from the payloadMessagebuffer message buffer.
static void transmitter_task(void* pvParameters) {
	MessageBufferHandle_t payloadMessagebuffer = (MessageBufferHandle_t)pvParameters;
	for (;;) {
		transmitter_sendMessage(payloadMessagebuffer);
	}
}

void transmitter_create(MessageBufferHandle_t messageBuffer, SemaphoreHandle_t _printMutex) {
	printMutex = _printMutex;
	xTaskCreate(
		transmitter_task, "transmitter task", 
		TASK_TRANSMITTER_TASK_STACK,
		messageBuffer, 
		TASK_TRANSMITTER_TASK_STACK, 
		NULL
	);
}