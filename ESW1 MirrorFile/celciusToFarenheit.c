#include <stdio.h>

int main(void){
	float fahr, celcius;
	int lower, upper, step;
	
	lower = 0;
	upper = 1000;
	step = 20;
	
	fahr = lower;
	printf("This is a farenheit to celcius conversion table\nFarenheit Celcius\n");
	while(fahr <= upper){
		celcius = (5.0/9.0) * (fahr-32.0);
		printf("%9.f %7.1f\n", fahr, celcius);
		fahr = fahr + step;
	}
	return 0;
}