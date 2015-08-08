//Esquema simples para mexer com Led, Buzzer e o LDR.
//O funcionamento básico é informar por meio de som e luz
//que a escuridão esta chegando (Analogic Max Value). Quando
//a escuridão chega uma sirene soa (Buzzer) e as luzes piscam
//sem parar, nesse caso os dois leds. Entretanto quand a escuridão
//esta se aproximando o Buzzer bipa apenas e um único Led pisca (LedAlert).

int pinBuzzer = 9;
int pinLedOk = 10;
int pinLedAlert = 11;
int pinLDR = 5; // Analogic pin

void setup() {
  // put your setup code here, to run once:
  pinMode(pinLedOk, OUTPUT);
  pinMode(pinLedAlert, OUTPUT);

  // para verificar os valores do LDR
  Serial.begin(9600);
  setupDone();  
}

void loop() {
  // put your main code here, to run repeatedly:
  int valueLDR = analogRead(pinLDR);
  Serial.println(valueLDR);

  if (valueLDR >= 30 && valueLDR <= 1022) { // Analogic pin max value 1023
    // dark is coming hehehe
    ledBlinkingNotDark();
    buzzerBippingNotDark();
  } else if (valueLDR >= 1023) {
    // dark arrived :(
    ledBlinkingConstantlyDark();
    buzzerBippingDarkArrived();
  }
}

void setupDone() {
  // pisca os leds para indicar que o setup foi iniciado
  // e o programa está carregado pronto para iniciar.
  digitalWrite(pinLedOk, HIGH);
  digitalWrite(pinLedAlert, HIGH);
  delay(1000);
  digitalWrite(pinLedOk, LOW);
  digitalWrite(pinLedAlert, LOW);
}

void ledBlinkingNotDark() {
  // pisca apenas o Led de Alerta para informar que esta 
  // chegando perto do limite (escuridão).
  digitalWrite(pinLedAlert, HIGH);
  delay(200);
  digitalWrite(pinLedAlert, LOW);
  delay(200);
}

void buzzerBippingNotDark() {
  // bipa o Buzzer para informar que esta 
  // chegando perto do limite (escuridão).
  tone(pinBuzzer, 1000);
  delay(500);
  noTone(pinBuzzer);
  delay(500);
}

void ledBlinkingConstantlyDark() {
  // pisca os dois leds para informar que o 
  // limite chegou
  digitalWrite(pinLedAlert, HIGH);
  digitalWrite(pinLedOk, HIGH);
  delay(200);
  digitalWrite(pinLedAlert, LOW);
  digitalWrite(pinLedOk, LOW);
  delay(200);
}

void buzzerBippingDarkArrived() {
  // Sirene de alerta, para informar a escuridão total
  float sinValue;
  int toneValue;
  
  // See code: https://github.com/tpiva/Arduino-Project/tree/master/Arduino/ProjetoBuzzerMelody Same as below.
  for (int x = 0; x < 180; x++) { // utilizamos até 179 para evitar valores negativos na função SIN -> Seno
    sinValue = (sin(x * (3.1416 / 180))); // Função seno requer valor em Radianos, por isso do PI / 180
    toneValue = 2000 + (int(sinValue * 1000)); // cast para int e soma 2000 + ValorX1000 para gerar uma valor adequado para a frequencia
    tone(pinBuzzer, toneValue); // produz o som no Pino 7 com uma frequencia -> toneVal
    delay(2);
  }
}

