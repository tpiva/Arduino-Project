// primeiro projeto com arduino --> led acendendo

int ledPin = 10;

void setup(){
  pinMode(ledPin, OUTPUT);
}

void loop(){
  
  digitalWrite(ledPin,HIGH);
  delay(500);
  digitalWrite(ledPin,LOW);
  delay(2000);
  
  for(int i = 0; i < 4; i++){
    digitalWrite(ledPin,HIGH);
    delay(500);
    digitalWrite(ledPin,LOW);
    delay(800);
  }
  
}
