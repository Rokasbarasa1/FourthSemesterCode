#include "gtest/gtest.h"

extern "C" {
#include "list.h"
}

class TestListCode : public ::testing::Test {
	list_t list;
	void SetUp() override {
		list_t list = list_createList();
	}

	void TearDown() override {
		list_destroy(list);
	}
};
//TESTING AN EMPTY LIST
TEST_F(TestListCode, TestCreateList) {
	list_t list2 = list_createList();
	EXPECT_TRUE(list2 != NULL);
}

TEST_F(TestListCode, TestDestroyListEmpty) {
	list_t list = list_createList();
	EXPECT_TRUE(list_destroy(list) == 0);
}

TEST_F(TestListCode, TestNoOfItemsEmpty) {
	list_t list = list_createList();
	EXPECT_TRUE(list_noOfItems(list) == 0);
}

TEST_F(TestListCode, TestGetItemEmpty) {
	list_t list = list_createList();
	int item;
	EXPECT_TRUE(list_getItem(list,(void**) &item, 0) == 2);
}

TEST_F(TestListCode, TestAddItemEmpty) {
	list_t list = list_createList();
	int item = 2;
	EXPECT_TRUE(list_addItem(list, (void*)item) == 0);
}

//Testing list with items in it

TEST_F(TestListCode, TestGetItem) {
	list_t list = list_createList();
	int item = 2;
	list_addItem(list, (void*)item);
	int item2;
	list_getItem(list, (void**)&item2, 0);
	EXPECT_TRUE( item == item2);
}

TEST_F(TestListCode, TestDestroyList) {
	list_t list = list_createList();
	int item = 2;
	list_addItem(list, (void*)item);
	EXPECT_TRUE(list_destroy(list) == 0);
}

TEST_F(TestListCode, TestRemoveItem) {
	list_t list = list_createList();
	int item = 2;
	list_addItem(list, (void*)item);
	list_removeItem(list, (void**)item);
	EXPECT_TRUE(list_getItem(list, (void**)&item, 0) == 2);
}
