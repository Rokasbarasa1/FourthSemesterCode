#include <stdio.h>
#include "FreeRTOS.h"
#include "task.h"
#include "semphr.h"

/* Priorities at which the tasks are created. */
#define	TASK_MY_C_TASK_PRIORITY			( tskIDLE_PRIORITY + 1 )
#define	TASK_MY_B_TASK_PRIORITY			( tskIDLE_PRIORITY + 1 )
#define	TASK_MY_A_TASK_PRIORITY			( tskIDLE_PRIORITY + 1 )
/* Task stack sizes*/
#define	TASK_MY_A_TASK_STACK			( configMINIMAL_STACK_SIZE )
#define	TASK_MY_B_TASK_STACK			( configMINIMAL_STACK_SIZE )
#define	TASK_MY_C_TASK_STACK			( configMINIMAL_STACK_SIZE )




/* Task Handles */
TaskHandle_t _taskSecondHandle = NULL;

SemaphoreHandle_t RIOsemaphore = NULL;
SemaphoreHandle_t OKsemaphore = NULL;

void TaskC(void* pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;

	for (;;)
	{
		xSemaphoreTake(OKsemaphore, portMAX_DELAY);
		vTaskDelay(pdMS_TO_TICKS(14));
		xSemaphoreTake(RIOsemaphore, portMAX_DELAY);
		printf("O ");
		printf("OK ");
		xSemaphoreGive(RIOsemaphore);
		xSemaphoreGive(OKsemaphore);
		vTaskDelay(pdMS_TO_TICKS(1000));
	}
}

// --------------------------------------------------------------------------------------
void TaskB(void* pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;

	for (;;)
	{
		vTaskDelay(pdMS_TO_TICKS(11));
		xSemaphoreTake(RIOsemaphore, portMAX_DELAY);
		printf("I ");
		xSemaphoreGive(RIOsemaphore);
		xSemaphoreTake(OKsemaphore, portMAX_DELAY);
		printf("OK\n");
		xSemaphoreGive(OKsemaphore);
		vTaskDelay(pdMS_TO_TICKS(1000));
	}
}

// --------------------------------------------------------------------------------------
void TaskA(void* pvParameters)
{
	// Remove compiler warnings.
	(void)pvParameters;

	for (;;)
	{
		
		xSemaphoreTake(RIOsemaphore, portMAX_DELAY);
		printf("R ");
		xSemaphoreGive(RIOsemaphore);

		xSemaphoreTake(OKsemaphore, portMAX_DELAY);
		printf("OK ");
		xSemaphoreGive(OKsemaphore);
		vTaskDelay(pdMS_TO_TICKS(1000));
	}
}

// --------------------------------------------------------------------------------------
void main(void)
{
	RIOsemaphore = xSemaphoreCreateBinary();
	OKsemaphore = xSemaphoreCreateBinary();
	xSemaphoreGive(RIOsemaphore);
	xSemaphoreGive(OKsemaphore);

	
	xTaskCreate(
		TaskC,
		"MyB",
		TASK_MY_C_TASK_STACK,
		(void*)1,
		TASK_MY_C_TASK_PRIORITY,
		&_taskSecondHandle);
	xTaskCreate(
		TaskA,
		"MyA",
		TASK_MY_A_TASK_STACK,
		(void*)1,
		TASK_MY_A_TASK_PRIORITY,
		&_taskSecondHandle);

	xTaskCreate(
		TaskB,
		"MyB",
		TASK_MY_B_TASK_STACK,
		(void*)1,
		TASK_MY_B_TASK_PRIORITY,
		&_taskSecondHandle);
	
	// Let the operating system take over :)
	vTaskStartScheduler();
}