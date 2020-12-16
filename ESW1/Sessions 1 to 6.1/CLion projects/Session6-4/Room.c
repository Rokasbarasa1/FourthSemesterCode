//
// Created by Rokas on 26/10/2020.
//

#include <string.h>
#include <corecrt_malloc.h>
#include "Room.h"

#include "Support/List.h"


typedef struct Room {
    char* _location;
    uint16_t _squareMeters;
    list_t* temperatureReaders;
    list_t* humidityReaders;
}Room;

static uint8_t calculateAverageHumidity(uint8_t humidities[2]) {
    uint8_t average = 0;
    for (int i = 0; i < sizeof(humidities)/sizeof(uint8_t); ++i) {
        average += humidities[i];
    }
    average = average / (sizeof(humidities)/sizeof(uint8_t));
    return average;
}

static float calculateAverageTemperature(float temperatures[4]) {
    float average = 0;
    for (int i = 0; i < sizeof(temperatures)/sizeof(float); ++i) {
        average += temperatures[i];
    }
    average = average / (sizeof(temperatures)/sizeof(float));
    return average;
}

static room_roomHealth_t _calculateHealth(float temp, uint8_t hum) {
    return (uint16_t)(temp * hum) % 5;// dummy calculation;
}

room_t room_create(char* location, uint16_t sqMeter) {
    room_t new_room = calloc(sizeof(Room), 1);
    if (NULL == new_room) {
        return NULL;
    }
    char* pointerLocation = calloc(sizeof(location), 1);
    strncpy(pointerLocation , location, sizeof(location) - 1);
    new_room->_location = pointerLocation;
    new_room->_squareMeters = sqMeter;
    new_room->temperatureReaders = list_createList();
    new_room->humidityReaders = list_createList();
    return new_room;
}

void room_destroy(room_t self){
    for (int i = 0; i < list_noOfItems(self->humidityReaders); ++i) {
        humidity_t * humidity;
        list_getItem(self->humidityReaders, humidity, i);
        humidity_destroy(humidity);
    }
    for (int i = 0; i < list_noOfItems(self->temperatureReaders); ++i) {
        temperature_t* temperature;
        list_getItem(self->temperatureReaders, temperature, i);
        temperature_destroy(temperature);
    }
    free(self);
}

void room_addTemperature(room_t self,temperature_t temp){
    if(list_noOfItems(self->temperatureReaders) < 4){
        list_addItem(self->temperatureReaders, temp);
    } else{
        //TODO ERROR
    }
}

void room_addHumidity(room_t self, humidity_t hum){
    if(list_noOfItems(self->humidityReaders) < 2){
        list_addItem(self->humidityReaders, hum);
    } else{
        //TODO ERROR
    }
}

room_roomHealth_t room_getRoomHealth(room_t self) {
    float temperatureMeassurement[4];
    for (int i = 0; i < list_noOfItems(self->temperatureReaders); ++i) {
        temperature_t* temperature;
        list_getItem(self->temperatureReaders, &temperature, i);
        temperature_meassure(temperature);
        temperatureMeassurement[i] = temperature_getTemperature(temperature);
    }

    uint8_t humidityMeassurement[2];
    for (int i = 0; i < list_noOfItems(self->humidityReaders); ++i) {
        humidity_t* humidity;
        list_getItem(self->humidityReaders, &humidity, i);
        humidity_meassure(humidity);
        humidityMeassurement[i] = humidity_getHumidity(humidity);
    }
    return _calculateHealth(calculateAverageTemperature(temperatureMeassurement), calculateAverageHumidity(humidityMeassurement));
}


char* room_getRoomHealthText(room_roomHealth_t health) {
    static const char* _health_text[] = { "PERFECT", "GOOD", "AVERAGE", "BAD", "DISASTER" };
    return _health_text[health];
}

char* room_getLocation(room_t self) {
    return self->_location;
}

uint16_t room_getArea(room_t self) {
    return self->_squareMeters;
}