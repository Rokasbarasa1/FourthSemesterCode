#include "my_string_function.h"
#include <stdio.h>

int read_line(char s[]) {
	int c = 0;
	int i = 0;
	int done = 0;

	do
	{
		c = getchar();
		if (c == EOF || c == '\n') {
			done = 1;
		}
	} while (!done);
	
	s[i] = '\0';
	return i;
}

void my_to_upper(char* str_in, char* str_out) {
	char smallLetters[27] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','\0'};
	char bigLetters[27] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','\0'};
	int index = 0;
	while (str_in[index] != '\0')
	{
		for (int i = 0; i < 27; i++)
		{
			if (smallLetters[i] == '\0') {
				str_out[index] = str_in[index];
				break;
			}
			else if (str_in[index] == smallLetters[i]) {
				str_out[index] = bigLetters[i];
			}
		}
		index++;
	}
}