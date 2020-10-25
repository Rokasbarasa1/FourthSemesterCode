#include "list.h"
#include <stdio.h>

typedef struct Node {
	void* value;
	Node* next;
}Node;

typedef struct List {
	Node* head;
} List;

ListReturnCode list_createList(list_t self) {
	list_t new_list = malloc(sizeof(List), 1);
	if (NULL == new_list) {
		return 3;
	}
	new_list->head = NULL;

}
ListReturnCode list_destroy(list_t self) {
	if (NULL != self) {
		free(self);
		return 1;
	}
	else {
		return 3;
	}
}
ListReturnCode list_addItem(list_t self, void* item) {
	Node* new_node = malloc(sizeof(Node));
	new_node->value = item;
	new_node->next = NULL;
	Node tail = list_findTail(self->head);
	tail.next = &new_node;
	return 1;
}
ListReturnCode list_getItem(list_t self, void** item, uint16_t index) {
	Node item_node = findNodeByIndex(self->head, index);
	*item = item_node.value;
	return 1;
}
ListReturnCode list_removeItem(list_t self, void* item) {
	Node node_to_delete = findNodeByItem(self->head, item);
	Node* nextTemp = node_to_delete.next;
	Node parentNode = findParentNodeofNode(self->head, &node_to_delete);
	parentNode.next = nextTemp;
	free(node_to_delete);
	return 1;
}
uint16_t list_noOfItems(list_t self) {
	return countToTail(self->head);
}

Node list_findTail(Node* head) {
	if (head->next == NULL) {
		return *head;
	}
	else
	{
		return list_findTail(head->next);
	}
}

uint16_t countToTail(Node* head) {
	if (head->next == NULL) {
		return 1;
	}
	else
	{
		return countToTail(head->next)+1;
	}
}

Node findNodeByIndex(Node* head, uint16_t index) {
	if (index != 0) {
		return findNodeByIndex(head->next,index - 1);
	}
	else
	{
		return *head;
	}
}

Node findNodeByItem(Node* head, void* item) {
	if (head->value == item) {
		return *head;
	}
	else if (head->next == NULL) {
		Node nullNode;
		return nullNode;
	}
	else {
		return findNodeByItem(head->next, item);
	}
}

Node findParentNodeofNode(Node* head, Node* childNode) {
	if (head->next == childNode) {
		return *head;
	}
	else if (head->next == NULL) {
		Node nullNode;
		return nullNode;
	}
	else
	{
		return findParentNodeofNode(head->next, childNode);
	}

}