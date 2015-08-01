// Set of pin on Arduino for Led, Push Button.
int led = 7;
int botao = 4;
int flag = 0;

void setup(){
  pinMode(led, OUTPUT); 
  pinMode(botao, INPUT); // Push button will be an input, depend on value of it to turn on LED.
}

void loop(){
   flag = digitalRead(botao); // Read value of pushButton
	//1 = push, 0 = pull
   if(flag == 1){
     digitalWrite(led, HIGH);
   }else{
     digitalWrite(led, LOW);
   }
}
