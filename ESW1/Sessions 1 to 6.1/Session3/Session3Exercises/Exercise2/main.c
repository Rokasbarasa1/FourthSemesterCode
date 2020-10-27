#include <stdio.h>

int main(void) {
	char text[] = "The quick brown fox jumps over the lazy dog";
	char* endS = text + 39;
	printf("The string now says: \"%s\n", text);

	*endS = '\0';
	printf("The string now says: \"%s\n", text);

	endS = endS - 1;
	*endS = '\0';
	printf("The string now says: \"%s\n", text);

	endS = endS - 4;
	*endS = '\0';
	printf("The string now says: \"%s\n", text);

	endS = endS - 18;
	*endS = '\0';
	printf("The string now says: \"%s\n", text);


	endS = endS + 10;
	*endS = '\0';
	printf("The string now says: \"%s\n", text);
	return 0;
}