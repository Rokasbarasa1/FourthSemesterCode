#include <stdio.h>
#include <stdint.h>
#include <stdbool.h>
#include <Windows.h>
#include "HealthHandler.h"

bool metricUnits = true;
static healthHandler_t* hh;
static room_t* livingRoom;
static room_t* kitchen;

void setupApplication(){
    hh = healthHandler_create();
    livingRoom = room_create("living room", 75);
    temperature_t* t0 = temperature_create(0);
    room_addTemperature(livingRoom, t0);
    temperature_t* t1 = temperature_create(1);
    room_addTemperature(livingRoom, t1);
    humidity_t* h1 = humidity_create(1);
    room_addHumidity(livingRoom, h1);
    healthHandler_addRoom(hh, livingRoom);

    kitchen = room_create("kitchen", 16);
    temperature_t* t2 = temperature_create(2);
    room_addTemperature(kitchen, t2);
    humidity_t* h2 = humidity_create(2);
    room_addHumidity(kitchen, h2);
    healthHandler_addRoom(hh, kitchen);
}

void delayMs(uint16_t ms){
    _sleep(ms);
}

void runApplication(){
    healthHandler_showBuildingHealth(hh);
    delayMs(10);
}

int main() {
    printf( "Hello, World!\n");
    setupApplication();
    runApplication();
    return 0;
}




