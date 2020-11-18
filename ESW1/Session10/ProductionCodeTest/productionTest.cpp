#include "gtest/gtest.h"

class TestProductionCode : public ::testing::Test{
protected:
	void SetUp() override {

	}
	void TearDown() override {

	}
};


TEST_F(TestProductionCode, TestCreate) {
  EXPECT_EQ(1, 1);
  EXPECT_TRUE(true);
}

TEST_F(TestProductionCode, TestAddItem) {
	EXPECT_EQ(2, 1);
	EXPECT_TRUE(true);
}