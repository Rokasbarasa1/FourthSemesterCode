#include <stdio.h>
#include "FreeRTOS.h"
#include "task.h"
#include "semphr.h"

/* Priorities at which the tasks are created. */
#define	TASK_MY_B_TASK_PRIORITY			( tskIDLE_PRIORITY + 1 )
#define	TASK_MY_A_TASK_PRIORITY			( tskIDLE_PRIORITY + 2 )
/* Task stack sizes*/
#define	TASK_MY_A_TASK_STACK			( configMINIMAL_STACK_SIZE )
#define	TASK_MY_B_TASK_STACK			( configMINIMAL_STACK_SIZE )



/* Task Handles */
TaskHandle_t _taskSecondHandle = NULL;
SemaphoreHandle_t sharedSemaphore = NULL;

void TaskB1(void* pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;

	for (;;)
	{
		xSemaphoreTake(sharedSemaphore, portMAX_DELAY);
		puts("Doing B stuff");
		xSemaphoreGive(sharedSemaphore);
		vTaskDelay(pdMS_TO_TICKS(200));
	}
}
// --------------------------------------------------------------------------------------

void TaskA1(void* pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;

	for (;;)
	{
		xSemaphoreTake(sharedSemaphore, portMAX_DELAY);
		puts("Doing A stuff");
		vTaskDelay(pdMS_TO_TICKS(1000));
		xSemaphoreGive(sharedSemaphore);
		vTaskDelay(pdMS_TO_TICKS(500));
	}
}

// --------------------------------------------------------------------------------------
void main(void)
{
	sharedSemaphore = xSemaphoreCreateBinary(); 
	xSemaphoreGive(sharedSemaphore);

	/* Create the task, not storing the handle. */
	/*
	xTaskCreate(
		taskMyTask,       // Function that implements the task.
		"MyTask",          // Text name for the task
		TASK_MY_TASK_STACK,      // Stack size in words, not bytes.
		(void*)1,    // Parameter passed into the task.
		TASK_MY_TASK_PRIORITY,// Priority at which the task is created.
		NULL);      //Used to pass out the created task's handle.
*/
	xTaskCreate(
		TaskA1,
		"MyA",
		TASK_MY_A_TASK_STACK,
		(void*)2,
		TASK_MY_A_TASK_PRIORITY,
		&_taskSecondHandle);

	xTaskCreate(
		TaskB1,
		"MyB",
		TASK_MY_B_TASK_STACK,
		(void*)1,
		TASK_MY_B_TASK_PRIORITY,
		&_taskSecondHandle);
	// Let the operating system take over :)
	vTaskStartScheduler();
}