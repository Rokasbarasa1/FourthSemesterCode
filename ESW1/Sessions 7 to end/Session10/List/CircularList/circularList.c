#pragma once
#include "circularList.h"
#include <stdlib.h>


typedef struct Node {
    void* value;
    struct Node* next;
}Node;

typedef struct CircularList {
    Node* head;
} CircularList;

circularList_t circularList_create() {
    circularList_t new_circle = calloc(sizeof(CircularList), 1);
    if (NULL == new_circle) {
        return NULL;
    }

    return new_circle;
}

int circularList_destroy(circularList_t list) {
    free(list);
    list = NULL;
    if (list == NULL) {
        return 1;
    }
    else
    {
        return 0;
    }
}

int circularList_put(circularList_t self, int* item) {
    if (self->head == NULL) {
        Node* newNode = calloc(sizeof(Node), 1);
        if (NULL == newNode) {
            return 0;
        }
        newNode->value = item;
        self->head = newNode;
        return 1;
    }
    else{
        return -1;
    }
}

int circularList_get(circularList_t self) {
    if (self->head != NULL) {
        return NULL;
    }
    else {
        return NULL;
    }
}

int circularList_isFull(circularList_t self) {
    if (self->head == NULL) {
        return 0;
    }
    else {
        return -1;
    }
}

int circularList_isEmpty(circularList_t self) {
    if (self->head == NULL) {
        return 1;
    }
    else {
        return 0;
    }
}