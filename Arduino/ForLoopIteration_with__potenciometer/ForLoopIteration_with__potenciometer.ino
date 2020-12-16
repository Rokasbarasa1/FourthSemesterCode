/*
  For Loop Iteration

  Demonstrates the use of a for() loop.
  Lights multiple LEDs in sequence, then in reverse.

  The circuit:
  - LEDs from pins 2 through 7 to ground

  created 2006
  by David A. Mellis
  modified 30 Aug 2011
  by Tom Igoe

  This example code is in the public domain.

  http://www.arduino.cc/en/Tutorial/ForLoop
*/

int timer = 1000;           // The higher the number, the slower the timing.

void setup() {
  // use a for loop to initialize each pin as an output:
  for (int thisPin = 3; thisPin < 9; thisPin++) {
    pinMode(thisPin, OUTPUT);
  }
  Serial.begin(9600);
}

void loop() {
  // loop from the lowest pin to the highest:
  for (int thisPin = 3; thisPin < 9; thisPin++) {
    int sensorValue = analogRead(A2);
    float voltage = 255 * (sensorValue / 1023.0);
    Serial.println(voltage);
    analogWrite(thisPin, voltage);
    delay(timer);
    analogWrite(thisPin, 0);
  }

  // loop from the highest pin to the lowest:
  for (int thisPin = 8; thisPin >= 3; thisPin--) {
    int sensorValue = analogRead(A2);
    float voltage = 255 * (sensorValue / 1023.0);
    Serial.println(voltage);
    analogWrite(thisPin, voltage);
    delay(timer);
    // turn the pin off:
    analogWrite(thisPin, 0);
  }
}
