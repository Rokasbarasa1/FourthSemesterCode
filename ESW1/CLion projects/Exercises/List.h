//
// Created by Rokas on 24/10/2020.
//

#ifndef EXERCISES_LIST_H
#define EXERCISES_LIST_H
#include <stdint.h>

typedef enum {
    OK,
    EMPTY,
    NOT_FOUND,
    NULLL,
    ERROR
}list_listReturnCode;

typedef struct List* list_t;
list_t list_createList();
list_listReturnCode list_destroy(list_t self);
list_listReturnCode list_addItem(list_t self, void* item);
list_listReturnCode list_getItem(list_t self, void** item, uint16_t index);
list_listReturnCode list_removeItem(list_t self, void* item);
uint16_t list_noOfItems(list_t self);

#endif //EXERCISES_LIST_H