int temPin = 0;
int ledPin1 = 8;
int ledPin2 = 10;
int ledPin3 = 11;
float getTemp;
float temp;

void setup(){
  Serial.begin(9600);
  pinMode(ledPin1,OUTPUT);
  pinMode(ledPin2,OUTPUT);
  pinMode(ledPin3,OUTPUT); 
}

void loop(){
  getTemp = analogRead(temPin);
  temp = (500*getTemp)/1023; 
  Serial.println(temp);
  
  if(temp < 25.00){
    analogWrite(ledPin1, HIGH);
    analogWrite(ledPin2, LOW);
    analogWrite(ledPin3, LOW);
  } else if(temp >= 25.00 && temp < 26.00){
   analogWrite(ledPin2, HIGH);
    analogWrite(ledPin1, LOW);
    analogWrite(ledPin3, LOW);
  } else {
   analogWrite(ledPin3, HIGH);
    analogWrite(ledPin2, LOW);
    analogWrite(ledPin1, LOW);
  }
  
}
