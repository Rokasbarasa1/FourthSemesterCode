
#include "gtest/gtest.h"

extern "C"
{
#include "FreeRTOS.h"
#include "MessagePayload.h"
}

static message_payload_t payload;

class TestMessagePayload : public ::testing::Test {
protected:
	void SetUp() override {
		payload = messagePayload_create(15,15);

	}
	void TearDown() override {
		free(payload);
	}
};

TEST_F(TestMessagePayload, MessagePayloadTestCreate) {
	message_payload_t payload = messagePayload_create(0, 0);
	ASSERT_NE(payload, nullptr);
}

//Zombie testing.
TEST_F(TestMessagePayload, MessagePayloadTestGetLength) {
	ASSERT_EQ(messagePayload_getLength(payload), 2);
}

TEST_F(TestMessagePayload, MessagePayloadTestGetByteZero) {
	message_payload_t payload = messagePayload_create(0, 0);

	ASSERT_EQ(messagePayload_getByte(payload, 0), 0x0);
	ASSERT_EQ(messagePayload_getByte(payload, 1), 0x0);
}

TEST_F(TestMessagePayload, MessagePayloadTestGetByteOne) {
	message_payload_t payload = messagePayload_create(1, 1);

	ASSERT_EQ(messagePayload_getByte(payload, 0), 0x1);
	ASSERT_EQ(messagePayload_getByte(payload, 1), 0x1);
}

TEST_F(TestMessagePayload, MessagePayloadTestGetByteEquivalencePartitioning) {
	free(payload);
	payload = messagePayload_create(5, 5);

	ASSERT_EQ(messagePayload_getByte(payload, 0), 0x5);
	ASSERT_EQ(messagePayload_getByte(payload, 1), 0x5);

	free(payload);
	payload = messagePayload_create(9, 9);

	ASSERT_EQ(messagePayload_getByte(payload, 0), 0x9);
	ASSERT_EQ(messagePayload_getByte(payload, 1), 0x9);
	free(payload);
	payload = messagePayload_create(13, 13);

	ASSERT_EQ(messagePayload_getByte(payload, 0), 0xd);
	ASSERT_EQ(messagePayload_getByte(payload, 1), 0xd);
	free(payload);
	payload = messagePayload_create(15, 15);

	ASSERT_EQ(messagePayload_getByte(payload, 0), 0xf);
	ASSERT_EQ(messagePayload_getByte(payload, 1), 0xf);
}

TEST_F(TestMessagePayload, MessagePayloadTestGetByteBeforeMany) {
	message_payload_t payload = messagePayload_create(29, 29);

	ASSERT_EQ(messagePayload_getByte(payload, 0), 0x1d);
	ASSERT_EQ(messagePayload_getByte(payload, 1), 0x1d);
}

TEST_F(TestMessagePayload, MessagePayloadTestGetByteMany) {
	message_payload_t payload = messagePayload_create(30, 30);

	ASSERT_EQ(messagePayload_getByte(payload, 0), 0x1e);
	ASSERT_EQ(messagePayload_getByte(payload, 1), 0x1e);
}