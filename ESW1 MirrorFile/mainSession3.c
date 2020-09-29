#include <stdio.h>

int main(void){
	double num1 = 7;
	double num2 = 3;
	double result = num1/num2;
	double result2 = num1%num2;
	printf("Before crash");
	printf("%0.1f",num1);
	printf("%0.1f",num2);
	printf("%0.1f",result);
	printf("%0.1f",result2);
	return 0;
}