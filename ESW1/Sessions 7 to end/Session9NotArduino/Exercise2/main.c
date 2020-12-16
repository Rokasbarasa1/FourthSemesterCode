#include <stdio.h>
#include "FreeRTOS.h"
#include "task.h"
#include "queue.h"
#include "semphr.h"


/* Priorities at which the tasks are created. */
#define TASK_A_TASK_PRIORITY	( tskIDLE_PRIORITY + 2 )
#define	TASK_B_TASK_PRIORITY	( tskIDLE_PRIORITY + 1 )
/* Task stack sizes*/
#define TASK_A_TASK_STACK		( configMINIMAL_STACK_SIZE )
#define	TASK_B_TASK_STACK		( configMINIMAL_STACK_SIZE )

/* Task Handles */
TaskHandle_t _taskSecondHandle = NULL;
QueueHandle_t queue = NULL;
SemaphoreHandle_t queuePriority = NULL;

// --------------------------------------------------------------------------------------
void taskA(void* pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;
	int value = 90;
	int value1 = 274;
	int value2 = 79;
	int value3 = 15;
	int value4 = 99;
	for (;;)
	{
		xSemaphoreTake(queuePriority, portMAX_DELAY);
		if (xQueueSendToBack(queue, (void*)&value, portMAX_DELAY)) {
			printf("Sent %d to queue\n", value);
		}else {
			xSemaphoreGive(queuePriority);
		}
		if (xQueueSendToBack(queue, (void*)&value1, portMAX_DELAY)) {
			printf("Sent %d to queue\n", value1);
		}
		else {
			xSemaphoreGive(queuePriority);
		}
		if (xQueueSendToBack(queue, (void*)&value2, portMAX_DELAY)) {
			printf("Sent %d to queue\n", value2);
		}
		else {
			xSemaphoreGive(queuePriority);
		}
		if (xQueueSendToBack(queue, (void*)&value3, portMAX_DELAY)) {
			printf("Sent %d to queue\n", value3);
		}
		else {
			xSemaphoreGive(queuePriority);
		}
		if (xQueueSendToBack(queue, (void*)&value4, portMAX_DELAY)) {
			printf("Sent %d to queue\n", value4);
		}
		else {
			xSemaphoreGive(queuePriority);
		}
		xSemaphoreGive(queuePriority);
		vTaskDelay(pdMS_TO_TICKS(500));
	}
}

void taskB(void* pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;
	for (;;)
	{
		xSemaphoreTake(queuePriority, portMAX_DELAY);
		int fromQueue = 0;
		if (xQueueReceive(queue, (void*)&fromQueue, portMAX_DELAY)) {
			printf("Hi from B: %d\n", fromQueue);
		}
		xSemaphoreGive(queuePriority);
		vTaskDelay(pdMS_TO_TICKS(150));
	}
}

// --------------------------------------------------------------------------------------
void main(void)
{
	queuePriority = xSemaphoreCreateBinary();
	xSemaphoreGive(queuePriority);

	queue = xQueueCreate(5, sizeof(int));
	/* Create the task, not storing the handle. */
	xTaskCreate(
		taskA,       /* Function that implements the task. */
		"MyTask",          /* Text name for the task. */
		TASK_A_TASK_STACK,      /* Stack size in words, not bytes. */
		(void*)1,    /* Parameter passed into the task. */
		TASK_A_TASK_PRIORITY,/* Priority at which the task is created. */
		NULL);      /* Used to pass out the created task's handle. */

		/* Create the task, storing the handle. */
	xTaskCreate(
		taskB,       /* Function that implements the task. */
		"MySecondTask",          /* Text name for the task. */
		TASK_B_TASK_STACK,      /* Stack size in words, not bytes. */
		(void*)2,    /* Parameter passed into the task. */
		TASK_B_TASK_PRIORITY,/* Priority at which the task is created. */
		&_taskSecondHandle);      /* Used to pass out the created task's handle. */

	// Let the operating system take over :)
	vTaskStartScheduler();
}