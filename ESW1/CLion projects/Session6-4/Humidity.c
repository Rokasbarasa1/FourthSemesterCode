//
// Created by Rokas on 26/10/2020.
//

#include "Humidity.h"
#include <stdint.h>
#include <corecrt_malloc.h>
#include "Drivers/HumidityDriver.h"
typedef struct Humidity{
    uint8_t latestHumidity;
    uint8_t _driverPort;
}Humidity;

humidity_t humidity_create(uint8_t portNo) {
    humidity_t new_humidity = calloc(sizeof(Humidity), 1);
    if (NULL == new_humidity) {
        return NULL;
    }
    new_humidity->latestHumidity = 0;
    new_humidity->_driverPort = portNo;
    humidityDriver_initDriver(portNo);
    return new_humidity;
}

void humidity_destroy(humidity_t self){
    free(self);
}

void humidity_meassure(humidity_t self) {
    self->latestHumidity = humidityDriver_getHumidity();
}

float humidity_getHumidity(humidity_t self) {
    return self->latestHumidity;
}

