#pragma once
#include <stdint.h>
typedef enum {
	OK,
	EMPTY,
	NOT_FOUND,
	NULLL,
	ERROR
}ListReturnCode;

typedef struct List* list_t;
ListReturnCode list_createList(list_t self);
ListReturnCode list_destroy(list_t self);
ListReturnCode list_addItem(list_t self, void* item);
ListReturnCode list_getItem(list_t self, void** item, uint16_t index);
ListReturnCode list_removeItem(list_t self, void* item);
uint16_t list_noOfItems(list_t self);