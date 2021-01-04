
#include "gtest/gtest.h"

extern "C"
{
#include "definitions.h"
#include "FreeRTOS.h"
#include "event_groups.h"
#include "WaterLevelSensor.h"
}

static EventGroupHandle_t eventReady;
static waterLevelSensor_t sensor;

class TestWaterLevelSensor : public ::testing::Test {
protected:
	void SetUp() override {
		eventReady = xEventGroupCreate();

		sensor = waterLevelSensor_create(eventReady);
	}
	void TearDown() override {
		vEventGroupDelete(eventReady);
		free(sensor);
	}
};

TEST_F(TestWaterLevelSensor, TestWaterLevelSensorTestCreate) {
	EventGroupHandle_t eventReadyNew = xEventGroupCreate();

	waterLevelSensor_t sensorNew = waterLevelSensor_create(eventReadyNew);
	ASSERT_NE(sensorNew, nullptr);
}

//Testing the responses with the thread method and without 
TEST_F(TestWaterLevelSensor, TestWaterLevelSensorTestEventBitNoThread) {
	EventBits_t data_bits = xEventGroupWaitBits(
		eventReady,
		DEF_BIT_DATA_READY_WATER_LEVEL,
		pdTRUE,
		pdTRUE,
		pdMS_TO_TICKS(120)
	);
	EXPECT_TRUE(data_bits == 0);
}

TEST_F(TestWaterLevelSensor, TestWaterLevelSensorTestEventBitThread) {
	EventBits_t data_bits = xEventGroupWaitBits(
		eventReady,
		DEF_BIT_DATA_READY_WATER_LEVEL,
		pdTRUE,
		pdTRUE,
		pdMS_TO_TICKS(120)
	);
	EXPECT_TRUE(data_bits == 0);
}

TEST_F(TestWaterLevelSensor, TestWaterLevelSensorTestMeasureLevel) {
	waterLevelSensor_measure(sensor);
}

TEST_F(TestWaterLevelSensor, TestWaterLevelSensorTestGetLevelNoThread) {
	EXPECT_EQ(waterLevelSensor_getLastWaterLevel(sensor), 30000);
}

TEST_F(TestWaterLevelSensor, TestWaterLevelSensorTestGetLevelThread) {
	waterLevelSensor_measure(sensor);
	waterLevelSensor_measureLevel(sensor);
	vTaskDelay(pdMS_TO_TICKS(120));

	uint16_t number = waterLevelSensor_getLastWaterLevel(sensor);
	EXPECT_TRUE(number > -1 && number < 30);
}