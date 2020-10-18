#include <stdio.h>
#include "mystr.h"
int my_strlen(const char* str) {
	int length = 0;
	int index = 0;
	int didNotReachEnd = 1;
	while (didNotReachEnd) {
		if (str[index] == '\0') {
			didNotReachEnd = 0;
		}
		else {
			length++;
			index++;
		}
	}
	return length;
}

int my_strcmp(const char* str1, const char* str2) {
	if (my_strlen(str1) == my_strlen(str2) ){
		for (int i = 0; i <= my_strlen(*str1); i++ ) {
			if (str1[i] != str2[i]) {
				return 0;
			}
		}
	}
	else {
		return 0;
	}
	return 1;
}

char* my_strcpy(char* dest, const char* src) {
	int i;
	unsigned int number = my_strlen(src);
	for (i = 0; i <= number; i++) {
		dest[i] = src[i];
	}
	return dest;
}

char* my_strdup(const char* str) {
	unsigned int length = my_strlen(str)+1;
	void* duplicate = malloc(length);
	if (duplicate == NULL) {
		return NULL;
	}
	return  (char*)my_strcpy(duplicate, str);
}
