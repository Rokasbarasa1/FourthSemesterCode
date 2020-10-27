//
// Created by Rokas on 26/10/2020.
//

#include <string.h>
#include "Room.h"
#include "Temperature.h"
#include "Humidity.h"

static char _location[30] = { 0 };
static uint16_t _squareMeters;

static room_roomHealth_t _calculateHealth(float temp, uint8_t hum) {
    return (uint16_t)(temp * hum) % 5;// dummy calculation;
}

room_t room_create(char* location, uint16_t sqMeter) {
    strncpy(_location, location, sizeof(_location) - 1); // Why does it look like this??
    _squareMeters = sqMeter;
}

void room_destroy(room_t self){

}

void room_addTemperature(room_t self,temperature_t temp){

}

void room_addHumidity(room_t self, humidity_t hum){

}

room_roomHealth_t room_getRoomHealth(room_t self) {
    temperature_meassure();
    humidity_meassure();

    return _calculateHealth(temperature_getTemperature(), humidity_getHumidity());
}

char* room_getRoomHealthText(room_t self, room_roomHealth_t health) {
    static const char* _health_text[] = { "PERFECT", "GOOD", "AVERAGE", "BAD", "DISASTER" };
    return _health_text[health];
}

char* room_getLocation(room_t self) {
    return _location;
}

uint16_t room_getArea(room_t self) {
    return _squareMeters;
}