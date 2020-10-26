//
// Created by Rokas on 26/10/2020.
//

#ifndef SESSION6_4_ROOM_H
#define SESSION6_4_ROOM_H

#include "Temperature.h"
#include "Humidity.h"
#include <stdint.h>

typedef enum {
    ROOM_PERFECT,
    ROOM_GOOD,
    ROOM_AVERAGE,
    ROOM_BAD,
    ROOM_DISASTER,
    ROOM_NOT_AVAILABLE
}room_roomHealth;

typedef struct Room room_t;
room_t room_create();
void room_destroy(room_t self);
void room_addTemperature(room_t self,temperature_t temp);
void room_addHumidity(room_t self, humidity_t hum);
room_roomHealth getRoomHealth(room_t self);
char* getRoomHealthText(room_t self, room_roomHealth health);
char* getLocation(room_t self);
uint16_t getArea(room_t self);

#endif //SESSION6_4_ROOM_H
