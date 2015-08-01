//projeto 3 -- LED pulsante

int ledPin = 3;
float sinVal;
int ledVal;

void setup(){

  pinMode(ledPin, OUTPUT);

  }

  void loop(){

    for(int x = 0; x < 180; x++){ // play with multiplex with some leds.

      sinVal = (sin(x*(3.1416/180)));
      ledVal = int(sinVal * 255);
      analogWrite(ledPin, ledVal);
      delay(25);  

    }

  }

