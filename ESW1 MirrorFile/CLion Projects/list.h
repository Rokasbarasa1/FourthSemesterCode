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
ListReturnCode createList(list_t self);
ListReturnCode destroy(list_t self);
ListReturnCode addItem(list_t self, void* item);
ListReturnCode getItem(list_t self, void** item, uint16_t index);
ListReturnCode removeItem(list_t self, void* item);
uint16_t noOfItems(list_t self);