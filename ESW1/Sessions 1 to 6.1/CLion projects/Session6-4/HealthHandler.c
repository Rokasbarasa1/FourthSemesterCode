//
// Created by Rokas on 26/10/2020.
//

#include "HealthHandler.h"
#include <stdio.h>
#include <corecrt_malloc.h>
#include "Room.h"
#include "Support/List.h"

typedef struct HealthHandler{
    list_t* rooms;
}HealthHandler;

static void _printRoomHealth(healthHandler_t self, uint8_t index) {
    room_t* room;
    list_getItem(self->rooms, &room, index);
    printf("%s: area: %d m2 Health: %s\n", room_getLocation(room), room_getArea(room), room_getRoomHealthText(room_getRoomHealth(room)));
}

healthHandler_t healthHandler_create() {
    healthHandler_t new_healthHandler = calloc(sizeof(HealthHandler), 1);
    if (NULL == new_healthHandler) {
        return NULL;
    }
    new_healthHandler->rooms = list_createList();
    return new_healthHandler;
}

void healthHandler_destroy(healthHandler_t self){
    for (int i = 0; i < list_noOfItems(self->rooms); ++i) {
        room_t* room;
        list_getItem(self->rooms, room, i);
        room_destroy(room);
    }
    free(self);
}

void healthHandler_addRoom(healthHandler_t self, room_t room){
    if(list_noOfItems(self->rooms) < 10){
        list_addItem(self->rooms, room);
    } else{
        //TODO ERROR
    }
}

void healthHandler_showBuildingHealth(healthHandler_t self) {
    for (int i = 0; i < list_noOfItems(self->rooms); ++i) {
        _printRoomHealth(self, i);
    }
}

