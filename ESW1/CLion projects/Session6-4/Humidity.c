//
// Created by Rokas on 26/10/2020.
//

#include "Humidity.h"
#include <stdint.h>
#include "HumidityDriver.h"

typedef struct Humidity{
    int8_t _latestHumidity = 0;
}Humidity;

humidity_t humidity_create(void) {
    humidityDriver_initDriver();
}

void humidity_destroy(humidity_t self){

}

void humidity_meassure(humidity_t self) {
    self._latestHumidity = humidityDriver_getHumidity();
}

float humidity_getHumidity(humidity_t self) {
    return self._latestHumidity;
}

