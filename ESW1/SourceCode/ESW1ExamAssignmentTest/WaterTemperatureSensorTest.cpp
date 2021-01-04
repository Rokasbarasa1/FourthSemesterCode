
#include "gtest/gtest.h"

extern "C"
{
#include "definitions.h"
#include "FreeRTOS.h"
#include "event_groups.h"
#include "WaterTemperatureSensor.h"
}

static EventGroupHandle_t eventReady;
static waterTemperatureSensor_t sensor;

class TestWaterTemperatureSensor : public ::testing::Test {
protected:
	void SetUp() override {
		eventReady = xEventGroupCreate();

		sensor = waterTemperatureSensor_create(eventReady);
	}
	void TearDown() override {
		vEventGroupDelete(eventReady);
		free(sensor);
	}
};

//All of these test are done without the thread working. So the values are basic.
TEST_F(TestWaterTemperatureSensor, WaterTemperatureSensorTestCreate) {
	EventGroupHandle_t eventReadyNew = xEventGroupCreate();

	waterTemperatureSensor_t sensorNew = waterTemperatureSensor_create(eventReadyNew);
	ASSERT_NE(sensorNew, nullptr);
}

TEST_F(TestWaterTemperatureSensor, WaterTemperatureSensorTestEventBitNoThread) {
	
	EventBits_t data_bits = xEventGroupWaitBits(
		eventReady, 
		DEF_BIT_DATA_READY_WATER_TEMPERATURE,
		pdTRUE, 
		pdTRUE, 
		pdMS_TO_TICKS(1)
	);
	EXPECT_TRUE(data_bits == 0);
}

TEST_F(TestWaterTemperatureSensor, WaterTemperatureSensorTestEventBitThread) {
	waterTemperatureSensor_measure(sensor);

	EventBits_t data_bits = xEventGroupWaitBits(
		eventReady,
		DEF_BIT_DATA_READY_WATER_TEMPERATURE,
		pdTRUE,
		pdTRUE,
		pdMS_TO_TICKS(120)
	);
	EXPECT_TRUE(data_bits == 2);
}

TEST_F(TestWaterTemperatureSensor, WaterTemperatureSensorTestGetTemperatureNoThread) {
	EXPECT_EQ(waterTemperatureSensor_getLastWaterTemperature(sensor), -1);
}

//This test is kind of stupid because i set default value to 0
TEST_F(TestWaterTemperatureSensor, WaterTemperatureSensorTestGetTemperatureThread) {
	waterTemperatureSensor_measure(sensor);
	vTaskDelay(pdMS_TO_TICKS(120));

	int8_t number = waterTemperatureSensor_getLastWaterTemperature(sensor);
	EXPECT_TRUE(number > -1 && number < 20 );
}