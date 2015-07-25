// Logon windows with RFID RC522 and became arduino UNO as a Keyboard Device
// How get code of keys are define in this code with some methods. This are from https://github.com/trusktr/arduino-keyboard-sketch/blob/master/arduino-keyboard-sketch.ino
// How read arduino is get from http://www.arduinoecia.com.br/2014/12/controle-de-acesso-modulo-rfid-rc522.html as reference
#include <SPI.h>
#include <MFRC522.h>

#define SS_PIN 10
#define RST_PIN 9
// Definicoes pino modulo RC522
MFRC522 mfrc522(SS_PIN, RST_PIN);

char st[20];
uint8_t buf[8] = { 0 };

void setup()
{
  // Inicia a serial
  Serial.begin(9600);
  // Inicia  SPI bus
  SPI.begin();
  // Inicia MFRC522
  mfrc522.PCD_Init();
}

void loop()
{
  // Aguarda a aproximacao do cartao
  if ( ! mfrc522.PICC_IsNewCardPresent())
  {
    return;
  }
  // Seleciona um dos cartoes
  if ( ! mfrc522.PICC_ReadCardSerial())
  {
    return;
  }
  String conteudo = "";
  byte letra;
  for (byte i = 0; i < mfrc522.uid.size; i++)
  {
    conteudo.concat(String(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " "));
    conteudo.concat(String(mfrc522.uid.uidByte[i], HEX));
  }
  //  Serial.println();
  //  Serial.print("Mensagem : ");
  conteudo.toUpperCase();

  // Testa se o cartao1 foi lido
  if (conteudo.substring(1) == "65 D9 87 3A")
  {
    //send senha
    const char * sequence[] = {"PUT PASSWORD HERE"};
    // Note that in the above sequence, the "1" and "!" refer to the same key, so we get two exclamation marks since shift is pressed.
    pressSequenceOfKeys(sequence, sizeof(sequence)/sizeof(char *)); // they will be all caps.
    releaseAllKeys();
    releaseAllModifiers();
  }

  // Testa se o cartao2 foi lido
  if (conteudo.substring(1) == "87 4B DC 8A")
  {
  }
  delay(1000);
}


void pressKey(String keyname) {
  pressKey(getKeycode(keyname));
}
void pressKey(int keycode) { // TODO: cycle the 6 key spots in the report buffer instead of just using buf[2] each time.
  buf[2] = keycode;
  Serial.write(buf, 8); // Send key report
}

void releaseKey(String keyname) {
  releaseKey(getKeycode(keyname));
}
void releaseKey(int keycode) {
  // find the keycode in the report buffer, then set it to zero.
  int i = 0;
  for (i = 2; i < 8; i++) {
    if (buf[i] == keycode) {
      buf[i] = 0;
    }
  }
  Serial.write(buf, 8); // Send key report
}
void releaseAllKeys() {
  int i = 0;
  for (i = 2; i < 8; i++) {
    buf[i] = 0;
  }
  Serial.write(buf, 8); // Send key report
}

void pressSequenceOfKeys(const char * keySequence[], int numberOfKeys) {
    // This function can be good for pressing a few keys while holding a modifier down for example.

    int i = 0;
    for (i=0; i<numberOfKeys; i++) {
        pressKey(keySequence[i]);
        releaseKey(keySequence[i]);
    }
}

void releaseAllModifiers() {
    buf[0] = B00000000;
    Serial.write(buf, 8); // Send key report
}

int getKeycode(String keyname) {
    String key = String(keyname); // Use a copy so that we don't mutate the user's String. Not sure if this is needed, but just in case. TODO: find out.
    key.toLowerCase();

    int keycode = 0; // keycode of zero means nothing pressed.

    // non-modifier keys
    if      (key == "a") { keycode =  4; }
    else if (key == "b") { keycode =  5; }
    else if (key == "c") { keycode =  6; }
    else if (key == "d") { keycode =  7; }
    else if (key == "e") { keycode =  8; }
    else if (key == "f") { keycode =  9; }
    else if (key == "g") { keycode = 10; }
    else if (key == "h") { keycode = 11; }
    else if (key == "i") { keycode = 12; }
    else if (key == "j") { keycode = 13; }
    else if (key == "k") { keycode = 14; }
    else if (key == "l") { keycode = 15; }
    else if (key == "m") { keycode = 16; }
    else if (key == "n") { keycode = 17; }
    else if (key == "o") { keycode = 18; }
    else if (key == "p") { keycode = 19; }
    else if (key == "q") { keycode = 20; }
    else if (key == "r") { keycode = 21; }
    else if (key == "s") { keycode = 22; }
    else if (key == "t") { keycode = 23; }
    else if (key == "u") { keycode = 24; }
    else if (key == "v") { keycode = 25; }
    else if (key == "w") { keycode = 26; }
    else if (key == "x") { keycode = 27; }
    else if (key == "y") { keycode = 28; }
    else if (key == "z") { keycode = 29; }

    else if (key == "1" || key == "!") { keycode = 30; }
    else if (key == "2" || key == "@") { keycode = 31; }
    else if (key == "3" || key == "#") { keycode = 32; }
    else if (key == "4" || key == "$") { keycode = 33; }
    else if (key == "5" || key == "%") { keycode = 34; }
    else if (key == "6" || key == "^") { keycode = 35; }
    else if (key == "7" || key == "&") { keycode = 36; }
    else if (key == "8" || key == "*") { keycode = 37; }
    else if (key == "9" || key == "(") { keycode = 38; }
    else if (key == "0" || key == ")") { keycode = 39; }

    else if (key == "enter" || key == "return")     { keycode = 40; }
    else if (key == "escape" || key == "")    { keycode = 41; }
    else if (key == "backspace" || key == "") { keycode = 42; }
    else if (key == "tab" || key == "	")      { keycode = 43; }
    else if (key == "space" || key == " ")      { keycode = 44; }

    else if (key == "-" || key == "_")  { keycode = 45; }
    else if (key == "=" || key == "+")  { keycode = 46; }
    else if (key == "[" || key == "{")  { keycode = 47; }
    else if (key == "]" || key == "}")  { keycode = 48; }
    else if (key == "\\" || key == "|") { keycode = 49; }
    else if (key == ";" || key == ":")  { keycode = 51; }
    else if (key == "'" || key == "\"") { keycode = 52; }
    else if (key == "`" || key == "~")  { keycode = 53; }
    else if (key == "," || key == "<")  { keycode = 54; }
    else if (key == "." || key == ">")  { keycode = 55; }
    else if (key == "/" || key == "?")  { keycode = 56; }

    // TODO: Fix these keycodes. V
    else if (key == "capslock")         { keycode = 58; }

    else if (key == "f1")      { keycode = 59; }
    else if (key == "f2")      { keycode = 60; }
    else if (key == "f3")      { keycode = 61; }
    else if (key == "f4")      { keycode = 62; }
    else if (key == "f5")      { keycode = 63; }
    else if (key == "f6")      { keycode = 64; }
    else if (key == "f7")      { keycode = 65; }
    else if (key == "f8")      { keycode = 66; }
    else if (key == "f9")      { keycode = 67; }
    else if (key == "f10")     { keycode = 68; }
    else if (key == "f11")     { keycode = 69; }
    else if (key == "f12")     { keycode = 70; }

    else if (key == "print_screen")    { keycode = 70; }
    else if (key == "scroll_lock")     { keycode = 71; }
    else if (key == "pause")           { keycode = 72; }
    else if (key == "insert")          { keycode = 73; }
    else if (key == "home")            { keycode = 74; }
    else if (key == "page_up")         { keycode = 75; }
    else if (key == "delete")          { keycode = 76; }
    else if (key == "end")             { keycode = 77; }
    else if (key == "page_down")       { keycode = 78; }

    else if (key == "right_arrow")     { keycode = 79; }
    else if (key == "left_arrow")      { keycode = 80; }
    else if (key == "down_arrow")      { keycode = 81; }
    else if (key == "up_arrow")        { keycode = 82; }

    else if (key == "numlock" || key == "clear")   { keycode = 83; }

    //TODO: keypad and miscellaneous keys if you want them.

    // modifier keys.
    else if (key == "left_control")  { keycode = 224; }
    else if (key == "left_shift")    { keycode = 225; }
    else if (key == "left_alt")      { keycode = 226; }
    else if (key == "left_gui")      { keycode = 227; }

    else if (key == "right_control") { keycode = 228; }
    else if (key == "right_shift")   { keycode = 229; }
    else if (key == "right_alt")     { keycode = 230; }
    else if (key == "right_gui")     { keycode = 231; }

    return keycode;
}
