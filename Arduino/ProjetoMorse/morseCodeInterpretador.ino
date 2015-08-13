// reference of following elements and structure: http://www.nu-ware.com/NuCode%20Help/index.html?morse_code_structure_and_timing_.htm
#define dotLenght 150
#define dashLenght (dotLenght*3)
#define pause2Element dotLenght
#define pause2Characters dashLenght
#define pause2Word (dotLenght*7)

int pinLed = 8;

void setup() {
  // put your setup code here, to run once:
  pinMode(pinLed, OUTPUT);

  Serial.begin(9600);
}

void loop() {
  String strMorse;
  // put your main code here, to run repeatedly:
  if (Serial.available() > 0) {
    strMorse = Serial.readString();
    // define char sequence to better translate each caracther into morse
    char charSequenceMorse[strMorse.length() + 1];
    strMorse.toCharArray(charSequenceMorse, strMorse.length() + 1);

    // need to check wach value of morse string to translate into signals.
    translateCharSequenceIntoMorseCode(charSequenceMorse);
  }
}

void translateCharSequenceIntoMorseCode(char morseSequence[]) {

  for (int index = 0; index < sizeof(morseSequence); index++) {
    char charOfSequence = toLowerCase(morseSequence[index]);
    // it's necessary to know the next element is character or element
    switch (charOfSequence) {
      case 'a':
        dotElement();
        digitalWrite(pinLed, LOW);
        delay(pause2Element);
        dashElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'b':
        break;
      case 'c':
        break;
      case 'd':
        break;
      case 'e':
        break;
      case 'f':
        break;
      case 'g':
        break;
      case 'h':
        break;
      case ' ':
        // blank space, pause between words
        delay(pause2Word);
        break;
      default:
        // pause between characters
        delay(pause2Characters);
        break;
    }

  }
}

void dotElement() {
  digitalWrite(pinLed, HIGH);
  delay(dotLenght);
}

void dashElement() {
  digitalWrite(pinLed, HIGH);
  delay(dashLenght);
}
