#include <stdio.h>
#include "FreeRTOS.h"
#include "task.h"
#include "semphr.h"

/* Priorities at which the tasks are created. */
#define	TASK_MY_xTask_TASK_PRIORITY					( tskIDLE_PRIORITY + 2 )
#define	TASK_MY_valuesTask_TASK_PRIORITY			( tskIDLE_PRIORITY + 1 )
/* Task stack sizes*/
#define	TASK_MY_valuesTask_TASK_STACK				( configMINIMAL_STACK_SIZE )
#define	TASK_MY_xTask_TASK_STACK					( configMINIMAL_STACK_SIZE )

/* Task Handles */
TaskHandle_t _taskSecondHandle = NULL;

SemaphoreHandle_t useZ = NULL;
SemaphoreHandle_t useY = NULL;
int y = 0;
int z = 0;
// --------------------------------------------------------------------------------------

int getY() {
	int tmp;
	xSemaphoreTake(useY, portMAX_DELAY);
	tmp = y;
	xSemaphoreGive(useY);
	return y;
}

int getZ() {
	int tmp;
	xSemaphoreTake(useZ, portMAX_DELAY);
	tmp = z;
	xSemaphoreGive(useZ);
	return z;
}

void setZ(int value){
	xSemaphoreTake(useZ, portMAX_DELAY);
	z = value;
	xSemaphoreGive(useZ);

}

void setY(int value) {
	xSemaphoreTake(useY, portMAX_DELAY);
	y = value;
	xSemaphoreGive(useY);
}

void valuesTask(void* pvParameters)
{
	for (;;) {
		setY(0);
		setZ(0);
		vTaskDelay(pdMS_TO_TICKS(103));
		setY(1);
		vTaskDelay(pdMS_TO_TICKS(103));
		setZ(2);
		puts("Done");
		vTaskDelay(pdMS_TO_TICKS(333));
	}
}

// --------------------------------------------------------------------------------------
void xTask(void* pvParameters)
{
	int x = 0;
	for (;;) {
		x = getY() + getZ();
		printf("X value is: %d\n", x);
		vTaskDelay(pdMS_TO_TICKS(100));
	}
}

// --------------------------------------------------------------------------------------
void main(void)
{
	useZ = xSemaphoreCreateMutex();
	useY = xSemaphoreCreateMutex();

	xSemaphoreGive(useZ);
	xSemaphoreGive(useY);

	xTaskCreate(
		valuesTask,
		"MyA",
		TASK_MY_valuesTask_TASK_STACK,
		(void*)1,
		TASK_MY_valuesTask_TASK_PRIORITY,
		&_taskSecondHandle);

	xTaskCreate(
		xTask,
		"MyB",
		TASK_MY_xTask_TASK_STACK,
		(void*)1,
		TASK_MY_xTask_TASK_PRIORITY,
		&_taskSecondHandle);


	// Let the operating system take over :)
	vTaskStartScheduler();
}