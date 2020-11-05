//
// Created by Rokas on 26/10/2020.
//

#ifndef SESSION6_4_HUMIDITY_H
#define SESSION6_4_HUMIDITY_H

#include <stdint.h>

typedef struct Humidity* humidity_t;
humidity_t humidity_create(uint8_t portNo);
void humidity_destroy(humidity_t self);
void humidity_meassure(humidity_t self);
float humidity_getHumidity(humidity_t self);

#endif //SESSION6_4_HUMIDITY_H
