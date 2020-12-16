#include "gtest/gtest.h"

extern "C" {
#include "circularList.h"
}

class TestListCode : public ::testing::Test {
protected:
	circularList_t list;
	void SetUp() override {
		list = circularList_create();
	}

	void TearDown() override {
		circularList_destroy(list);
	}
};

//TESTS FOR EMPTY CIRCULAR LIST
TEST_F(TestListCode, TestCreateList) {
	circularList_t list2 = circularList_create();
	EXPECT_TRUE(list2 != NULL);
}

TEST_F(TestListCode, TestDestroyEmpty) {
	circularList_t list2 = circularList_create();
	EXPECT_TRUE(circularList_destroy(list2) == 1);
}

TEST_F(TestListCode, TestIsEmptyEmpty) {
	EXPECT_TRUE(circularList_isEmpty(list) == 1);
}

TEST_F(TestListCode, TestGetEmpty) {
	EXPECT_TRUE(circularList_get(list) == NULL);
}

TEST_F(TestListCode, TestIsFullEmpty) {
	EXPECT_TRUE(circularList_isFull(list) == 0);
}

TEST_F(TestListCode, TestPutEmpty) {
	int number = 21;
	EXPECT_TRUE(circularList_put(list, &number) == 1);
	EXPECT_TRUE(circularList_isEmpty(list) == 0);
}

//TESTS FOR NOT EMPTY CIRCULAR LIST

TEST_F(TestListCode, TestDestroyEmpty) {
	circularList_t list2 = circularList_create();
	EXPECT_TRUE(circularList_destroy(list2) == 1);
}
