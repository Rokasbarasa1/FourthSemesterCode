#include <stdbool.h>
#include <tempDriver.h>
#include "temperature.h"

extern bool temperature_metricUnits; // Declaration

static float _latestTemp = 0.0;
static uint8_t _driverPort;

static float _calculateTemp(float voltage) {
	return 15.0+(voltage * 5.0); // dummy calc
}

void temperature_create(uint8_t portNo) {
	_driverPort = portNo;
	temperatureDriver_initDriver(portNo);
}

void temperature_meassure(void) {
	_latestTemp = _calculateTemp(temperatureDriver_getVoltage());
}

float temperature_getTemperature(void){
	float _tmp = _latestTemp;
	if (temperature_metricUnits) {
		_tmp *= 0.2; // dummy conversion
	}

	return _tmp;
}
