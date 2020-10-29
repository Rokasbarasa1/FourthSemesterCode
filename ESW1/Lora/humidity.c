#include <stdint.h>
#include <humidityDriver.h>
#include "humidity.h"

static int8_t _latestHumidity = 0;

void humidity_create(void) {
	humidityDriver_initDriver();
}

void humidity_meassure(void) {
	_latestHumidity = humidityDriver_getHumidity();
}

uint8_t humidity_getHumidity(void) {
	return _latestHumidity;
}