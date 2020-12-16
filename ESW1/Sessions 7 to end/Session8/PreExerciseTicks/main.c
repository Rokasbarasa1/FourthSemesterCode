#include <stdio.h>
#include "FreeRTOS.h"
#include "task.h"

/* Priorities at which the tasks are created. */
#define TASK_B_Task_PRIORITY	( tskIDLE_PRIORITY + 1 )
#define	TASK_A_TASK_PRIORITY	( tskIDLE_PRIORITY + 2 )
#define	TASK_C_TASK_PRIORITY	( tskIDLE_PRIORITY + 2 )

/* Task stack sizes*/
#define TASK_B_TASK_STACK		( configMINIMAL_STACK_SIZE )
#define	TASK_A_TASK_STACK		( configMINIMAL_STACK_SIZE )
#define	TASK_C_TASK_STACK		( configMINIMAL_STACK_SIZE )


/* Task Handles */
TaskHandle_t _taskSecondHandle = NULL;

// --------------------------------------------------------------------------------------
void TaskA(void* pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;
	TickType_t lastWakeTime = xTaskGetTickCount();
	for (;;)
	{
		vTaskDelay(lastWakeTime, pdMS_TO_TICKS(200));
		puts("Hi from My Task");
	}
}

// --------------------------------------------------------------------------------------
void TaskB(void* pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;
	TickType_t lastWakeTime = xTaskGetTickCount();
	for (;;)
	{
		vTaskDelay(lastWakeTime, pdMS_TO_TICKS(300));
		puts("Hi from My Second Task");
	}
}

void TaskC(void* pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;
	TickType_t lastWakeTime = xTaskGetTickCount();
	for (;;)
	{
		vTaskDelayUntil(lastWakeTime, pdMS_TO_TICKS(700));
		puts("Hi from My Task");
	}
}

// --------------------------------------------------------------------------------------
void main(void)
{
	/* Create the task, not storing the handle. */
	xTaskCreate(
		TaskA,       /* Function that implements the task. */
		"MyTask",          /* Text name for the task. */
		TASK_A_TASK_STACK,      /* Stack size in words, not bytes. */
		(void*)1,    /* Parameter passed into the task. */
		TASK_A_TASK_PRIORITY,/* Priority at which the task is created. */
		NULL);      /* Used to pass out the created task's handle. */

		/* Create the task, storing the handle. */
	xTaskCreate(
		TaskB,       /* Function that implements the task. */
		"MySecondTask",          /* Text name for the task. */
		TASK_B_TASK_STACK,      /* Stack size in words, not bytes. */
		(void*)2,    /* Parameter passed into the task. */
		TASK_B_Task_PRIORITY,/* Priority at which the task is created. */
		NULL);      /* Used to pass out the created task's handle. */

	xTaskCreate(
		TaskC,       /* Function that implements the task. */
		"MyThirdTask",          /* Text name for the task. */
		TASK_C_TASK_STACK,      /* Stack size in words, not bytes. */
		(void*)3,    /* Parameter passed into the task. */
		TASK_C_TASK_PRIORITY,/* Priority at which the task is created. */
		NULL);      /* Used to pass out the created task's handle. */
	// Let the operating system take over :)
	vTaskStartScheduler();
}