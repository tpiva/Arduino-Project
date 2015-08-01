// first project of Arduino

int ledPin = 10; // define pin for LED

// Method that configure pin IN/OUT, Serial, Band of Serial Arduino COM and others things. 
// In other words, arduino knows its componets here.
void setup(){
  pinMode(ledPin, OUTPUT); // declare with OUTPUT in Arduino LED in PIN 10
}

void loop(){
  
  digitalWrite(ledPin,HIGH); // Write the OUTPUT, in this case TURN ON LED - HIGH
  delay(500);				// Simple delay to see what happens
  digitalWrite(ledPin,LOW); // Write the OUTPUT, in this case TURN OFF LED - LOW
  delay(2000);
  
  for(int i = 0; i < 4; i++){ // Turn on and off LED several times, 4.
    digitalWrite(ledPin,HIGH);
    delay(500);
    digitalWrite(ledPin,LOW);
    delay(800);
  }
  
}
