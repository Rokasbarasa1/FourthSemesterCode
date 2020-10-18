#include <stdio.h>
#include "mystr.h"

int main() {
	//lenght
	char string[] = "Geekssss";

	int len = my_strlen(&string);

	printf("Lenght of the string is %d\n", len);

	//Compare
	char sttring2[] = "Greeks";

	int compare = my_strcmp(&string, &sttring2);
	
	if (compare == 1) {
		printf("Strings are equal\n");
	}
	else {
		printf("Strings are not equal\n");
	}

	//copy
	
	char string3[100];
	my_strcpy(string3, string);

	printf("String that was copied: %s", &string);
	printf("String that was copied to: %s", &string3);

	//Dupllicate 

	char* target = my_strdup(string);
	
	printf("Duplicated string: %s", target);
	free(target);
}