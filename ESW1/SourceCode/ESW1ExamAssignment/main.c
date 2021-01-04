#include "FreeRTOS.h"
#include "task.h"

#include "AppController.h"

void main(void){
	appController_create(); //Assembles and manages the entire application
	vTaskStartScheduler(); //Will start the tasks that were created in the objects
	//Timing of tasks and other stuff is placed in definitions.h 
	//Not all code is commented as there is a lot of similairties between classes.
}