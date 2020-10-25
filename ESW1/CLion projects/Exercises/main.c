#include <stdio.h>
#include <stdlib.h>
#include "List.h"

int main() {
    printf("Hello, World!\n");
    list_t list;
    list = list_createList();
    int num1 = 8;
    int num2 = 567;
    int num3 = 444;

    list_addItem(list, num1);
    list_addItem(list, num2);
    list_addItem(list, num3);

    int numGet;
    list_getItem(list, &numGet, 0);
    printf("%d\n", numGet);
    list_getItem(list, &numGet, 1);
    printf("%d\n", numGet);
    list_getItem(list, &numGet, 2);
    printf("%d\n", numGet);
    numGet = list_noOfItems(list);
    printf("List length: %d\n", numGet);
    numGet = 567;
    list_removeItem(list,&numGet);
    list_getItem(list, &numGet, 0);
    printf("%d\n", numGet);
    list_getItem(list, &numGet, 1);
    printf("%d\n", numGet);
    if(list_destroy(list) == 1){
        printf("List destroyed");
    } else{
        printf("List not destroyed");
    }
    return 0;
}
