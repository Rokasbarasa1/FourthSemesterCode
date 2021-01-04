
#include "gtest/gtest.h"

extern "C"
{
#include "FreeRTOS.h"
#include "event_groups.h"
#include "AppController.h"
}

class TestAppController : public ::testing::Test {
protected:
	void SetUp() override {

	}
	void TearDown() override {

	}
};

//Testing to see if it crashes.
TEST_F(TestAppController, AppControllerTestCreate) {
	appController_create();
}

//Due to having everything created in the app controller i cannot test event bit setting.
TEST_F(TestAppController, AppControllerTestThreadNoEvent) {
	appController_create();
	appController_collectMeasurements();
}