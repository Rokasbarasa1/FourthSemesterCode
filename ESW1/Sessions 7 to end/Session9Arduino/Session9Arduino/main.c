/*
 * Session9Arduino.c
 *
 * Created: 10/11/2020 12:18:38
 * Author : Rokas
 */ 
#include <stdio.h>
#include <stdio_driver.h>

#include <avr/io.h>
#include <avr/interrupt.h>
#include <util/delay.h>


int main(void)
{
   stdioCreate(0); //Use USART 0 for stdio
   sei(); // Enable interrupt
   
   puts("Program started");
   
   uint16_t counter = 0;
   
   while(1){
	   printf("The counter value: %05d and in hex %04x\n");
	   counter++;
	   if(stdioInputWaiting())
	   {
			printf("###>%c\n", getchar());   
	   }
	   
	   _delay_ms(4000);
   }
}

