/*
    DistanceBySoftwareSerial.pde - URM 37 Control Library Version 2.0.0
    Author: Miles Burton, miles@mnetcs.com
    Copyright (c) 2009 Miles Burton All Rights Reserved
  
    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.
  
    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.
  
    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
  
*/
 
// # This library is a improvement version of URM 37 Control Library Version 2.0.0 from Miles Burton
// # Original Author: Miles Burton, miles@mnetcs.com
// #
// # Editor     : Lauren from DFRobot
// # Date       : 08.05.2012
 
// # Product name: URM V3.2 ultrasonic sensor TTL connection with Arduino
// # Product SKU : SEN0001
// # Version     : 1.0
// # Update the library to make it compatible with the Arduino IDE 1.0 or the latest version
 
// # Description:
// # The sketch for using the URM37 from DFRobot based on the Software Serial library
// # You could drive the URM37 with 2 digital pins on your Arduino board, and it works stable.
 
// # Connection:
// #       VCC (Arduino)   -> Pin 1 VCC (URM37 V3.2)
// #       GND (Arduino)   -> Pin 2 GND (URM37 V3.2)
// #       Pin 6 (Arduino) -> Pin 9 TXD (URM37 V3.2)
// #       Pin 7 (Arduino) -> Pin 8 RXD (URM37 V3.2)
// #
 
#include "URMSerial.h"
 
// The measurement we're taking
#define DISTANCE 1
#define TEMPERATURE 2
#define ERROR 3
#define NOTREADY 4
#define TIMEOUT 5
 
URMSerial urm;
int ledPin = 3;
float sinVal;
int ledVal;
 
void setup() {
 
  Serial.begin(9600);                  // Sets the baud rate to 9600
  urm.begin(6,7,9600);                 // RX Pin, TX Pin, Baud Rate
  pinMode(ledPin, OUTPUT);
   
}
 
void loop()
{
  Serial.println(getMeasurement());  // Output measurement
  
  if(getMeasurement() < 100) {
     for(int x = 0; x < 180; x++){

      sinVal = (sin(x*(3.1416/180)));
      ledVal = int(sinVal * 255);
      analogWrite(ledPin, ledVal);
      delay(25);  

    }
  } else {
     digitalWrite(ledPin,LOW);
  }
  //Serial.write(getMeasurement());
  delay(50);
}
 
 
int value; // This value will be populated
int getMeasurement()
{
  // Request a distance reading from the URM37
  switch(urm.requestMeasurementOrTimeout(DISTANCE, value)) // Find out the type of request
  {
  case DISTANCE: // Double check the reading we recieve is of DISTANCE type
    //    Serial.println(value); // Fetch the distance in centimeters from the URM37
    return value;
    break;
  case TEMPERATURE:
    return value;
    break;
  case ERROR:
    //Serial.println("Error");
    return -1;
    break;
  case NOTREADY:
    //Serial.println("Not Ready");
    return -2;
    break;
  case TIMEOUT:
    //Serial.println("Timeout");
    return -3;
    break;
  }
 
  return -1;
}
