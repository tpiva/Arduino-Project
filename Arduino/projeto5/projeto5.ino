int ledPin1=11;
int ledPin2=10;
char c;

void setup(){

  Serial.begin(9600);

  pinMode(ledPin1,OUTPUT);
  pinMode(ledPin2,OUTPUT);

}

void loop(){

  //inicializa uma variavel do tipo char chamada tecla
  char tecla;

  // armazena em "tecla" o retorno da função read()
  //essa função lê um valor que é escrito na porta serial
  tecla = Serial.read();

  //verfica se a tecla digitada é igual a l (liga)
  //se for igual entra na condiçao e liga o led
  if(tecla == 'a')
  {
    digitalWrite(ledPin1, HIGH);
  }

  else if(tecla == 'd')

  {
    digitalWrite(ledPin1, LOW);
    digitalWrite(ledPin2,HIGH);
  } 
  else {
    digitalWrite(ledPin1,LOW);
    digitalWrite(ledPin2,LOW);
  }

  delay(1000);

}




