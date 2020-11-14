#include <stdio.h>
#include <FreeRTOSConfig.h>


BaseType_t xTaskCreate(TaskFunction_t pvTaskCode, const char* constpcName, configSTACK_DEPTH_TYPE usStackDepth, void* pvParameters, UBaseType_t uxPriority, TaskHandle_t* pxCreatedTask);

#definetask1_TASK_PRIORITY(tskIDLE_PRIORITY + 5)
TaskHandle_tx1Handle = NULL;

void main(void) {
	vTaskStartScheduler();
	xTaskCreate(vTask1,
		"Task 1", 
		configMINIMAL_STACK_SIZE, 
		NULL, 
		task1_TASK_PRIORITY, 
		&x1Handle);

	vTaskStartScheduler();
	while (1) {
		; 
	}
	printf("Hello world");
	return 0;
}

void vTask1(void* pvParameters) {
	// Remove compiler warnings if pvParametersnot used
	(void)pvParameters;
	while(1) {
		PORTB = 0xFE;
		vTaskDelay(500);  // 500 time ticks
	}
}
