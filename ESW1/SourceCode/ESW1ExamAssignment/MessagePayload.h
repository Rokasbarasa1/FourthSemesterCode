#pragma once
#ifndef MESSAGE_PAYLOAD_H
#define MESSAGE_PAYLOAD_H
#include <stdint.h>

typedef struct MessagePayload* message_payload_t;
message_payload_t messagePayload_create(uint16_t waterLevel, int8_t temperature);
uint8_t messagePayload_getByte(message_payload_t self, uint8_t index);
uint8_t messagePayload_getLength(message_payload_t self);
#endif
