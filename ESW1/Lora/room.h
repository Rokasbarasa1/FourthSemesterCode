#pragma once
#include <stdint.h>
typedef enum {
	PERFECT
	,GOOD
	,AVERAGE
	,BAD
	,DISASTER
}room_roomHealth_t;

void room_create(char* location, uint16_t sqMeter);
room_roomHealth_t room_getRoomHealth(void);
char* room_getRoomHealthText(room_roomHealth_t health);
char* room_getLocation(void);
uint16_t room_getArea(void);