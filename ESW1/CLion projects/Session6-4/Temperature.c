//
// Created by Rokas on 26/10/2020.
//

#include "Temperature.h"
#include <stdbool.h>
#include <corecrt_malloc.h>
#include "Drivers/TempDriver.h"

//extern bool temperature_metricUnits; // Declaration

typedef struct Temperature {
    //TODO METRIC UNITS
    float _latestTemp;
    uint8_t _driverPort;
}Temperature;

static float _calculateTemp(float voltage) {
    return 15.0+(voltage * 5.0); // dummy calc
}

temperature_t* temperature_create(uint8_t portNo) {
    temperature_t new_temperature = calloc(sizeof(Temperature), 1);
    if (NULL == new_temperature) {
        return NULL;
    }
    new_temperature->_latestTemp = 0.0;
    new_temperature->_driverPort = portNo;
    temperatureDriver_initDriver(portNo);
    return new_temperature;
}

void temperature_destroy(temperature_t self){
    free(self);
}

void temperature_meassure(temperature_t self) {
    self->_latestTemp = _calculateTemp(temperatureDriver_getVoltage());
}

float temperature_getTemperature(temperature_t self){
    float _tmp = self->_latestTemp;
    if (true) {
        _tmp *= 0.2; // dummy conversion
    }
    return _tmp;
}