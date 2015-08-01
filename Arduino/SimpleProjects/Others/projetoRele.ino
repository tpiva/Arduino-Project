// Rele work like another component from Arduino Code perspective. I used an Arduino Rele for this example.
int rele = 8; // Set Rele pin

void setup()
{
  pinMode(rele, OUTPUT);  
}
void loop()
{
  digitalWrite(rele, HIGH); // Turn on Rele
  delay(10000);             // wait 10 s
  digitalWrite(rele, LOW);  // Turn off Rele
  delay(10000);             // wait 3 s
}

