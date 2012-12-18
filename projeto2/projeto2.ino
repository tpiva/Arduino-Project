int ledPin2 = 9;
int ledPin1= 10;
int ledPin3 = 11;

void setup(){

  pinMode(ledPin1,OUTPUT);
  pinMode(ledPin2,OUTPUT);
  pinMode(ledPin3,OUTPUT);
  
}

void loop(){

  
  digitalWrite(ledPin1,HIGH);
  delay(5000);
  
  digitalWrite(ledPin1,LOW);
  digitalWrite(ledPin2,HIGH);
  delay(3000);
  
  digitalWrite(ledPin2,LOW);
  digitalWrite(ledPin3,HIGH);
  delay(5000);
  digitalWrite(ledPin3,LOW);

}
