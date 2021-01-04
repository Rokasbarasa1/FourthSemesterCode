#pragma once
#ifndef TRANSMITTER_H
#define WATER_TEMPERATURE_SENSOR_H

#include <FreeRTOSConfig.h>
#include "semphr.h"
#include "message_buffer.h"

#include "MessagePayload.h"

void transmitter_create(MessageBufferHandle_t messageBuffer, SemaphoreHandle_t _printMutex);
void transmitter_sendMessage(MessageBufferHandle_t payloadMessagebuffer);
#endif