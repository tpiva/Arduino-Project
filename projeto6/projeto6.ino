//projeto - 6 - Efeito sequencial Leds
//principal aprendizado uso de vetores

/*
 * Principio a cada determinado periodo de tempo os leds irão apagando e acendendo
 * esse tempo é obtido pela função milissegundos
 *
 */

int ledPin = 10;
//The setup function is called once at startup of the sketch
void setup()
{
pinMode(ledPin, OUTPUT);
// Add your initialization code here
}

// The loop function is called in an endless loop
void loop()
{
    digitalWrite(ledPin,HIGH);
//Add your repeated code here
}
