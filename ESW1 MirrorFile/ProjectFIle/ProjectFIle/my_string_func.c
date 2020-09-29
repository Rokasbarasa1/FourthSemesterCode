#include <stdio.h>
#include "my_string_func.h"

int power(int x, int y) {
	if (y == 1) {
		return x;
	}
	else if (y == 0) {
		return 1;
	}
	else
	{
		return x * power(x, y - 1);
	}
}
void multiSwap(int* x, int* y, int* z) {
	int temp, temp1;
	temp = *x;
	temp1 = *y;
	*x = *z;
	*y = temp;
	*z = temp1;
}