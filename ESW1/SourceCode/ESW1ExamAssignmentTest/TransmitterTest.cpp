
#include "gtest/gtest.h"

extern "C"
{
#include "FreeRTOS.h"
#include "message_buffer.h"
#include "semphr.h"
#include "definitions.h"
#include "Transmitter.h"
#include "MessagePayload.h"
}
static MessageBufferHandle_t payloadMessagebuffer;

static SemaphoreHandle_t printMutex;

class TestTransmitter : public ::testing::Test {
protected:
	void SetUp() override {
		
	}
	void TearDown() override {

	}
};

//All of these test are done without the thread working. So the values are basic.
TEST_F(TestTransmitter, TransmitterTestCreate) {
	printMutex = xSemaphoreCreateMutex();
	payloadMessagebuffer = xMessageBufferCreate(sizeof(message_payload_t) * 2);
	transmitter_create(payloadMessagebuffer, printMutex);
}

//The only way to verify that this works good is to check if the message buffer is empty
TEST_F(TestTransmitter, TransmitterTestThread) {
	printMutex = xSemaphoreCreateMutex();
	payloadMessagebuffer = xMessageBufferCreate(sizeof(message_payload_t) * 2);
	transmitter_create(payloadMessagebuffer, printMutex);

	message_payload_t payload = messagePayload_create(1, 1);
	xMessageBufferSend(payloadMessagebuffer, &payload, sizeof(message_payload_t), pdMS_TO_TICKS(200));
	transmitter_sendMessage(payloadMessagebuffer);
	ASSERT_TRUE(xMessageBufferIsEmpty(payloadMessagebuffer) == pdTRUE);
}
