//
// Created by Rokas on 26/10/2020.
//

#include "HealthHandler.h"
#include <stdio.h>
#include "Room.h"
#include "List.h"

typedef struct HealthHandler{
    list_t rooms;
}HealthHandler;

static void _printRoomHealth(healthHandler_t self) {
    printf("%s: area: %d m2 Health: %s\n", room_getLocation(), room_getArea(), room_getRoomHealthText(room_getRoomHealth()));
}

void healthHandler_create(void) {
    // do something
}

void healthHandler_destroy(healthHandler_t self){

}

void healthHandler_addRoom(healthHandler_t self, room_t room){

}

void healthHandler_showBuildingHealth(healthHandler_t self) {
    _printRoomHealth();
}

