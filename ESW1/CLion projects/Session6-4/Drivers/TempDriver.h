//
// Created by Rokas on 27/10/2020.
//

#ifndef SESSION6_4_TEMPDRIVER_H
#define SESSION6_4_TEMPDRIVER_H

#include <stdint.h>

void temperatureDriver_initDriver(uint8_t port);
float temperatureDriver_getVoltage();

#endif //SESSION6_4_TEMPDRIVER_H
