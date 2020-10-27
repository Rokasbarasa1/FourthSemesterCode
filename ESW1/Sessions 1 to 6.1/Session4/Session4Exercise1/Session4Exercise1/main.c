#include <stdio.h>
#include "mystr.h"

int main() {
	//lenght
	char string[] = "Geekssss";

	int len = my_strlen(&string);
	printf("Lenght of the string is %d\n", len);
	printf("\n");

	//Compare
	printf("Compare");
	char string2[] = "Greeks";

	int compare = my_strcmp(&string, &string2);
	if (compare == 1) {
		printf("Comparison: Strings are equal\n");
	}
	else {
		printf("Comparison: Strings are not equal\n");
	}
	printf("\n");

	//copy
	char string3[100];

	my_strcpy(string3, string);
	printf("String that was copied: %s\n", &string);
	printf("String that was copied to: %s\n", &string3);
	printf("\n");

	//Duplicate 
	char* target = my_strdup(string);

	printf("Duplicated string: %s\n", target);
	free(target);
}