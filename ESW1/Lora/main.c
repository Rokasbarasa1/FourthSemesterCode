#include <time.h>
#include <stdbool.h>
#include "temperature.h"
#include "humidity.h"
#include "room.h"
#include "healtHandler.h"

bool temperature_metricUnits = true; // Definition

static void _delayMs(int milliseconds)
{
	long _pause;
	clock_t _now, _then;

	_pause = milliseconds * (CLOCKS_PER_SEC / 1000);
	_now = _then = clock();
	while ((_now - _then) < _pause)
		_now = clock();
}

static void _setupApplication(void) {
	temperature_create(0);
	humidity_create();
	room_create("Living Room", 75);
	healthHandler_create();
}

static void _runApplication(void) {
	while (1) {
		healthHandler_showBuildingHealth();
		_delayMs(1000);
	}
}

int main(void) {
	_setupApplication();
	_runApplication();

	return 0;
}