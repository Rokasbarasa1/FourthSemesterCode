#pragma once
#ifndef APP_CONTROLLER_H
#define APP_CONTROLLER_H

void appController_create();
//I placed the thread bodies in the headers for it to be more convenient.
//If it was done correctly the body of the task would be placed in a seperate class
//and acessed trough a sperate header
void appController_collectMeasurements();
#endif

