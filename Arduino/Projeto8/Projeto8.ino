int ledDelay;
int potPin = 2;
byte Pinosled[]={8,13,10};

void setup(){

  Serial.begin(9600);
  for(int i = 0 ; i < 3; i++){
    pinMode(Pinosled[i],OUTPUT);
  }
}

void loop(){
  
  ledDelay = analogRead(potPin);
  Serial.println(ledDelay);
  
  if(ledDelay < 512){
    digitalWrite(Pinosled[0],HIGH);
    digitalWrite(Pinosled[1],LOW);
    digitalWrite(Pinosled[2],LOW);
  } else if(ledDelay >= 512 && ledDelay < 1023){
    digitalWrite(Pinosled[1],HIGH);
    digitalWrite(Pinosled[0],LOW);
    digitalWrite(Pinosled[2],LOW);
  } else {
    digitalWrite(Pinosled[2],HIGH);
    digitalWrite(Pinosled[0],LOW);
    digitalWrite(Pinosled[1],LOW);
  }

}
