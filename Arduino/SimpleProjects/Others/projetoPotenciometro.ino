int potenciometro = 0; // Set potentiometer pin
int valor = 0;   // Storage potentiometer value
int led = 6 ; // Set pin of LED

void setup() {
  Serial.begin(9600);

  pinMode(led, OUTPUT);
}

void loop() {
  valor = analogRead(potenciometro); // Get value of potentiometer, it came from analogic port. 
  if(valor > 0){
    analogWrite(led, (valor/4)); // Turn on led according value of potentiometer. 
    Serial.println(valor); // Shows on monitor
  }
}
