// Projeto Alarme
float sinVal;
int toneVal;

void setup() {
  // put your setup code here, to run once:
  pinMode(7,OUTPUT);  
}

// OBS: Experimente alterar a multiplicação do sinVal * 1000, mudando o valor de 1000, aumentará a frenquencia e o som fica mais rápido.
// assim como o valor de 2000
void loop() {
  // put your main code here, to run repeatedly:
  for(int x=0; x <180; x++) { // utilizamos até 179 para evitar valores negativos na função SIN -> Seno
    sinVal = (sin(x*(3.1416/180)));  // Função seno requer valor em Radianos, por isso do PI / 180
    toneVal = 2000 + (int(sinVal*1000)); // cast para int e soma 2000 + ValorX1000 para gerar uma valor adequado para a frequencia
    tone(7, toneVal); // produz o som no Pino 7 com uma frequencia -> toneVal
    delay(2);
  }
}
