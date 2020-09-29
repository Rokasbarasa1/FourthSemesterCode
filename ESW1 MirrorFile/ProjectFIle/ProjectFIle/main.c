#include <stdio.h>
#include "my_string_func.h" // Dependency for interface declaration
int main(void) {
	//Decalring of variables for the swap
	int x = 2;
	int y = 3;
	int z = 4;
	printf("9 to the power of 5 is: %d\n", power(9, 5));

	printf("Before swap x=%d y=%d z=%d\n", x, y, z);
	multiSwap(&x, &y, &z);
	printf("Before swap x=%d y=%d z=%d\n", x, y, z);

	return 0;
}