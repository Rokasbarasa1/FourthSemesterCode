#include "gtest/gtest.h"

extern "C" {
#include "list.h"
}

class TestListCode : public ::testing::Test{
	void SetUp() override {

	}
	
	void TearDown() override {

	}
};



TEST_F(TestListCode, TestCreate) {
	EXPECT_EQ(1, 1);
	EXPECT_TRUE(true);
}