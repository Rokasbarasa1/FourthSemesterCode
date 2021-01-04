#pragma once
#ifndef WATER_TEMPERATURE_SENSOR_H
#define WATER_TEMPERATURE_SENSOR_H

#include <stdint.h>

#include "event_groups.h"

typedef struct WaterTemperatureSensor* waterTemperatureSensor_t;
waterTemperatureSensor_t waterTemperatureSensor_create(EventGroupHandle_t event_group_data_ready);
int8_t waterTemperatureSensor_getLastWaterTemperature(waterTemperatureSensor_t self);
void waterTemperatureSensor_measure(waterTemperatureSensor_t self);
#endif

 