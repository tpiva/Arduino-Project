/**
 * Projeto simulando efeito de fogo com LED's.
 */
#include "Projeto7.h"
#include "Arduino.h"

int ledPin1 = 9;
int ledPin2 = 10;
int ledPin3 = 11;

void setup()
{
    //define as variaveis referentes aos pinos, nos quais os LED estão conectados como saída
    pinMode(ledPin1,OUTPUT);
    pinMode(ledPin2,OUTPUT);
    pinMode(ledPin3,OUTPUT);
}

void loop()
{
    //cria o efeito de fogo, no qual os LED's acompanham uma onda.
    analogWrite(ledPin1,random(120) + 136);
    analogWrite(ledPin2,random(120) + 136);
    analogWrite(ledPin3,random(120) + 136);
    delay(1 + random(100));
}
