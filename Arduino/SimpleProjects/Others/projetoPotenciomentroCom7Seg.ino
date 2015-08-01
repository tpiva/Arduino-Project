//Pinos Display			                        Pinos do Arduino
//7				->				2
//6				->				3
//4				->				3
//2				->				5
//1				->				6
//9				->				7
//10				->				8
//5				->				9
//8				->				Gnd
//3				->				Gnd

int potenciometro = 0;
int valor = 0;
// Matrix of number to display on 7-Segments display -- 1 = turn ON and 0 - turn off
byte seven_seg_digits[10][7] = { { 1, 1, 1, 1, 1, 1, 0 }, // = 0
  { 0, 1, 1, 0, 0, 0, 0 }, // = 1
  { 1, 1, 0, 1, 1, 0, 1 }, // = 2
  { 1, 1, 1, 1, 0, 0, 1 }, // = 3
  { 0, 1, 1, 0, 0, 1, 1 }, // = 4
  { 1, 0, 1, 1, 0, 1, 1 }, // = 5
  { 1, 0, 1, 1, 1, 1, 1 }, // = 6
  { 1, 1, 1, 0, 0, 0, 0 }, // = 7
  { 1, 1, 1, 1, 1, 1, 1 }, // = 8
  { 1, 1, 1, 0, 0, 1, 1 } // = 9
};

void setup() {
  Serial.begin(9600);
  // configure port for each segment of display of 7-segments
  pinMode(2, OUTPUT);
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  pinMode(8, OUTPUT);
  pinMode(9, OUTPUT);
}

void loop() {
  valor = analogRead(potenciometro); // Get value of potentiometer
  if (valor > 0) {
    Serial.println(valor); //Show value of potentiometer on monitor

    String nova = String(valor); // Convert int value of potentiometer into String to display correct value on 7 segments display.
    if (nova.startsWith("1")) {
      sevenSegWrite(1);
    } else if (nova.startsWith("2")) {
      sevenSegWrite(2);
    } else if (nova.startsWith("3")) {
      sevenSegWrite(3);
    } else if (nova.startsWith("4")) {
      sevenSegWrite(4);
    } else if (nova.startsWith("5")) {
      sevenSegWrite(5);
    } else if (nova.startsWith("6")) {
      sevenSegWrite(6);
    } else if (nova.startsWith("7")) {
      sevenSegWrite(7);
    } else if (nova.startsWith("8")) {
      sevenSegWrite(8);
    } else if (nova.startsWith("9")) {
      sevenSegWrite(9);
    } else {
      sevenSegWrite(0);
    }

  }

}
// Method to display the correct number on 7 segments display.
void sevenSegWrite(byte digit) {
  byte pin = 2;
  for (byte segCount = 0; segCount < 7; ++segCount) {
    digitalWrite(pin, seven_seg_digits[digit][segCount]); // Simply get value of Matrix
    ++pin;
  }
}

