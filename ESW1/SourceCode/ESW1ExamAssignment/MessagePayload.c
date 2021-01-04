#include <stdio.h>

#include "FreeRTOS.h"

#include "MessagePayload.h"

//MessagePayload is a data object (struct) that contains the length of the message, as well as the individual
//bytes included in the message.

//The only way i could think of creating the message payload was to create an object to hold it.
//Technicaly a struct still contains the variables.
typedef struct MessagePayload {
    uint8_t length;
    uint8_t bytes[2];
}MessagePayload;

message_payload_t messagePayload_create(uint16_t waterLevel, int8_t temperature) {

    message_payload_t payload = calloc(sizeof(MessagePayload), 1);
    
    if (NULL == payload) {
        return NULL;
    }
    //Converts the values to hex format and sets them to a char array.
    //length from what i understand is number of values passed meaning level and temperature.
    payload->length = 2;
    payload->bytes[0] = waterLevel & 0xFF;
    payload->bytes[1] = temperature & 0xFF;

    return payload;
}

uint8_t messagePayload_getByte(message_payload_t self, uint8_t index) {
    return self->bytes[index];
}

uint8_t messagePayload_getLength(message_payload_t self) {
    return self->length;
}