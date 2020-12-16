#pragma once

typedef struct CircularList* circularList_t;
circularList_t circularList_create();
int circularList_destroy(circularList_t list);
int circularList_put(circularList_t self, int* item);
int circularList_get(circularList_t self);
int circularList_isFull(circularList_t self);
int circularList_isEmpty(circularList_t self);