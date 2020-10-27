#include <stdio.h>

void main(void) {
	int seven = 7;
	int three = 3;

	printf("The result of 7 division by 3 is: %d\n", seven / three);
	printf("The remainder of 7 division by 3 is: %d\n", seven % three);


	float seven1 = 7;
	float three1 = 3;

	printf("The result of 7 division by 3 is: %f\n", seven1 / three1);
	//printf("The remainder of 7 division by 3 is: %f\n", seven1 % three1); float division remainder does not function
	return 0;
}