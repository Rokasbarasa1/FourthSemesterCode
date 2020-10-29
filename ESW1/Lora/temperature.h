#pragma once
#include <stdint.h>

void temperature_create(uint8_t portNo);
void temperature_meassure(void);
float temperature_getTemperature(void);