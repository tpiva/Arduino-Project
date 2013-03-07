int ledDelay;
int ldrPin = 1;
int ledPin = 10;

void setup(){

  Serial.begin(9600);
  pinMode(ledPin,OUTPUT);
}

void loop(){
  
  ledDelay = analogRead(ldrPin);
  Serial.println(ledDelay);
  
  if(ledDelay > 100 ){
    digitalWrite(ledPin,LOW);
  } else {
        digitalWrite(ledPin,HIGH);
  }
}
