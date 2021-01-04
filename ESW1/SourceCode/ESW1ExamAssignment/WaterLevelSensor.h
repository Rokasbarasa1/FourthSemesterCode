#pragma once
#ifndef WATER_LEVEL_SENSOR_H
#define WATER_LEVEL_SENSOR_H

#include <stdint.h>

#include "event_groups.h"

typedef struct WaterLevelSensor* waterLevelSensor_t;
waterLevelSensor_t waterLevelSensor_create(EventGroupHandle_t event_group_data_ready);
void waterLevelSensor_measure(waterLevelSensor_t self);
uint16_t waterLevelSensor_getLastWaterLevel(waterLevelSensor_t self);
void waterLevelSensor_measureLevel(waterLevelSensor_t self);
#endif

