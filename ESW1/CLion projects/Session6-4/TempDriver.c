//
// Created by Rokas on 27/10/2020.
//

#include "TempDriver.h"
#include <time.h>
#include <stdlib.h>

void temperatureDriver_initDriver(uint8_t port) {
    srand(time(NULL));
}

float temperatureDriver_getVoltage() {
    int r = (rand() % (50 - 0 + 1));
    return ((double)r + 0.5) / 10.0;
}