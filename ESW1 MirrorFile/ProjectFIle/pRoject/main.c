#include <stdio.h>
#include "my_string_function.h"

#define MAX_LENGTH 1000;
int main(void) {
	char line[1000];
	char upper[1000];
	int line_length;

	while (read_line(line) != '\0')
	{
		my_to_upper(*line, *upper);
		printf("Length: %d\t%s\n", upper);
	}
	return 0;
}