#include "gtest/gtest.h"

extern "C"
{
#include "production.h"
}
class TestProductionCode : public ::testing::Test{
protected:
	void SetUp() override {

	}
	void TearDown() override {

	}
};


TEST_F(TestProductionCode, TestCreate) {
	//Arange
	//Act
	int n = sum(3, 4);
	//Assert
	EXPECT_EQ(7, n);
	//EXPECT_TRUE(true);
}
/*
TEST_F(TestProductionCode, TestAddItem) {
	EXPECT_EQ(2, 1);
	EXPECT_TRUE(true);
}
*/