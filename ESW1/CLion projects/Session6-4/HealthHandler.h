//
// Created by Rokas on 26/10/2020.
//

#ifndef SESSION6_4_HEALTHHANDLER_H
#define SESSION6_4_HEALTHHANDLER_H

#include "Room.h"

typedef struct HealthHandler healthHandler_t;
healthHandler_t healthHandler_create();
void healthHandler_destroy(healthHandler_t self);
void addRoom(healthHandler_t self, room_t room);
void showBuildingHealth(healthHandler_t self, healthHandler_t);

#endif //SESSION6_4_HEALTHHANDLER_H
