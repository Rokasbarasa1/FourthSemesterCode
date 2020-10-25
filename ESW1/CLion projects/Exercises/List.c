//
// Created by Rokas on 24/10/2020.
//

#include "List.h"
#include <stdlib.h>

typedef struct Node {
    void* value;
    struct Node* next;
}Node;

typedef struct List {
    Node* head;
} List;

Node* list_findTail(Node* head) {
    if (!head->next) {
        return head;
    }
    else
    {
        return list_findTail(head->next);
    }
}

uint16_t list_countToTail(Node* head) {
    if (head->next == NULL) {
        return 1;
    }
    else
    {
        return list_countToTail(head->next)+1;
    }
}

Node* list_findNodeByIndex(Node* head, uint16_t index) {
    if (index != 0) {
        return list_findNodeByIndex(head->next,index - 1);
    }
    else
    {
        return head;
    }
}

Node* list_findNodeByItem(Node* head, void** item) {
    if (head->value == *item) {
        return head;
    }
    else if (!head->next) {
        return NULL;
    }
    else {
        return list_findNodeByItem(head->next, item);
    }
}

Node* list_findParentNodeOfNode(Node* head, Node* childNode) {
    if (head->next == childNode) {
        return head;
    }
    else if (!head->next) {
        return NULL;
    }
    else
    {
        return list_findParentNodeOfNode(head->next, childNode);
    }
}

list_listReturnCode list_deleteNodesCascade(Node* head){
    if(!head){
        return 1;
    } else{
        if(head->next){
            Node* next = head->next;
            free(head);
            list_deleteNodesCascade(next);
        } else{
            free(head);
            return 1;
        }
    }
}

list_t list_createList() {
    list_t new_list = calloc(sizeof(List), 1);
    if (NULL == new_list) {
        return NULL;
    }
    return new_list;
}

//Destroys all elements in the list and thenthe list itself.
list_listReturnCode list_destroy(list_t self) {
    if (NULL != self) {
        list_deleteNodesCascade(self->head);
        free(self);
        return 1;
    } else {
        return 3;
    }
}

list_listReturnCode list_addItem(list_t self, void* item) {
    Node* new_node = calloc(sizeof(Node), 1);
    new_node->value = item;
    if(!self->head){
        self->head = new_node;
    } else{
        Node* tail = list_findTail(self->head);
        tail->next = new_node;
    }
    return 1;
}

list_listReturnCode list_getItem(list_t self, void** item, uint16_t index) {
    Node* item_node = list_findNodeByIndex(self->head, index);
    *item = item_node->value;
    return 1;
}

list_listReturnCode list_removeItem(list_t self, void* item) {
    Node* node_to_delete = list_findNodeByItem(self->head, item);
    Node* nextTemp = node_to_delete->next;
    if(self->head == node_to_delete){
        self->head = nextTemp;
    } else{
        Node* parentNode = list_findParentNodeOfNode(self->head, node_to_delete);
        parentNode->next = nextTemp;
    }
    free(node_to_delete);
    return 1;
}

uint16_t list_noOfItems(list_t self) {
    return list_countToTail(self->head);
}