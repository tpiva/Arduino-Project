// reference of following elements and structure: http://www.nu-ware.com/NuCode%20Help/index.html?morse_code_structure_and_timing_.htm
// table of codes: http://morsecode.scphillips.com/morse2.html
#define dotLenght 200
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
    // print string
    Serial.println(strMorse);
    // define char sequence to better translate each caracther into morse
    char charSequenceMorse[strMorse.length() + 1];
    strMorse.toCharArray(charSequenceMorse, strMorse.length() + 1);
    // need to check wach value of morse string to translate into signals.
    translateCharSequenceIntoMorseCode(charSequenceMorse,sizeof(charSequenceMorse));
  }
}

void translateCharSequenceIntoMorseCode(char morseSequence[], int sizeofCharArray) {
  for (int index = 0; index < sizeofCharArray; index++) {
    char charOfSequence = toLowerCase(morseSequence[index]);
    // it's necessary to know the next element is character or element
    switch (charOfSequence) {
      case 'a': // .-
        dotElement();
        turnOffLedToElement();
        dashElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'b': // -...
        dashElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'c': // -.-.
        dashElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dotElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'd': // -..
        dashElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'e': // .
        dotElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'f': //..-.
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dotElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'g': // --.
        dashElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dotElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'h': // ....
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'i': // ..
        dotElement();
        turnOffLedToElement();
        dotElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'j': //.---
        dotElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dashElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'k': // -.-
        dashElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dashElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'l': // .-..
        dotElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'm': // --
        dashElement();
        turnOffLedToElement();
        dashElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'n': // -.
        dashElement();
        turnOffLedToElement();
        dotElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'o': // ---
        dashElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dashElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'p': // .--.
        dotElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dotElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'q': // --.-
        dashElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dashElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'r': // .-.
        dotElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dotElement();
        digitalWrite(pinLed, LOW);
        break;
      case 's': // ...
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        digitalWrite(pinLed, LOW);
        break;
      case 't': // -
        dashElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'u': // ..-
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dashElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'v': // ...-
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dashElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'w': // .--
        dotElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dashElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'x': // -..-
        dashElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dashElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'y': // -.--
        dashElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dashElement();
        digitalWrite(pinLed, LOW);
        break;
      case 'z': // --..
        dashElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        digitalWrite(pinLed, LOW);
        break;
      case '0': // -----
        dashElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dashElement();
        digitalWrite(pinLed, LOW);
        break;
      case '1': // .----
        dotElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dashElement();
        digitalWrite(pinLed, LOW);
        break;
      case '2': // ..---
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dashElement();
        digitalWrite(pinLed, LOW);
        break;
      case '3': // ...--
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dashElement();
        digitalWrite(pinLed, LOW);
        break;
      case '4': // ....-
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dashElement();
        digitalWrite(pinLed, LOW);
        break;
      case '5': // .....
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        digitalWrite(pinLed, LOW);
        break;
      case '6': // -....
        dashElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        digitalWrite(pinLed, LOW);
        break;
      case '7': // --...
        dashElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        digitalWrite(pinLed, LOW);
        break;
      case '8': // ---..
        dashElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dotElement();
        turnOffLedToElement();
        dotElement();
        digitalWrite(pinLed, LOW);
        break;
      case '9': // ----.
        dashElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dashElement();
        turnOffLedToElement();
        dotElement();
        digitalWrite(pinLed, LOW);
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

void turnOffLedToElement() {
  digitalWrite(pinLed, LOW);
  delay(pause2Element);
}
