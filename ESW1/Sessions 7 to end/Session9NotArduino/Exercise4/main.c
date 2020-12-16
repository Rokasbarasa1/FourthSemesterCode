#include <stdio.h>
#include "FreeRTOS.h"
#include "task.h"
#include "queue.h"

/* Priorities at which the tasks are created. */
#define TASK_A_TASK_PRIORITY	( tskIDLE_PRIORITY + 1 )
#define	TASK_B_TASK_PRIORITY	( tskIDLE_PRIORITY + 2 )
/* Task stack sizes*/
#define TASK_A_TASK_STACK		( configMINIMAL_STACK_SIZE )
#define	TASK_B_TASK_STACK		( configMINIMAL_STACK_SIZE )

/* Task Handles */
TaskHandle_t _taskSecondHandle = NULL;
QueueHandle_t queue = NULL;
// --------------------------------------------------------------------------------------
void taskA(void* pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;
	double value = 90.455;
	for (;;)
	{
		if (xQueueSendToBack(queue, (void*)&value, portMAX_DELAY)) {
			printf("Sent %f to queue\n", value);
		}
		vTaskDelay(pdMS_TO_TICKS(50));
	}
}

void taskB(void* pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;
	for (;;)
	{
		double fromQueue = 0;
		if (xQueueReceive(queue, (void*)&fromQueue, portMAX_DELAY)) {
			printf("Hi from B: %f\n", fromQueue);
		}
		vTaskDelay(pdMS_TO_TICKS(100));
	}
}

// --------------------------------------------------------------------------------------
void main(void)
{
	queue = xQueueCreate(5, sizeof(double));
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