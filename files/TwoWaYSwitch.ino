/* 2016-05-16
 * Author: Johnatan Sona.
 * 
 * This is a statemachine that handles the communication between
 * the arduino and raspberry pi. It makes sure that data can be sent
 * both to and from the arduino. 
 * It uses AddicoreRFID library to handle the RFID-scanner.
 */

#include <AddicoreRFID.h>
#include <SPI.h>
#define  uchar unsigned char
#define uint  unsigned int

// create AddicoreRFID object to control the RFID module
AddicoreRFID myRFID;

//set the pins
const int chipSelectPin = 10;
const int NRSTPD = 5;


//Maximum length of the array
  #define MAX_LEN 16
  
  
  const int inputListener = 0;
  const int outputGreen = 1;
  const int outputRed = 2;
  const int inputRfid = 3;
  const int timeout = 4;

  int currentState = inputRfid;
  int nextState = inputListener;
  int incomingByte = 0;
  int tic = 0;
  char recieved = 'X';
void setup() {
  currentState = inputRfid;
  Serial.begin(9600);

  SPI.begin();
  
  pinMode(chipSelectPin,OUTPUT);              // Set digital pin 10 as OUTPUT to connect it to the RFID /ENABLE pin 
    digitalWrite(chipSelectPin, LOW);         // Activate the RFID reader
  pinMode(NRSTPD,OUTPUT);                     // Set digital pin 10 , Not Reset and Power-down
    digitalWrite(NRSTPD, HIGH);
  pinMode(7, OUTPUT);
  pinMode(8, OUTPUT);
  pinMode(4, OUTPUT);
  pinMode(3, OUTPUT);
  myRFID.AddicoreRFID_Init();
}

void loop() {
  
  switch(currentState){
    case inputRfid:
    digitalWrite(4, LOW);
        uchar status;
        uchar str[MAX_LEN];
  //Find tags, return tag type
  status = myRFID.AddicoreRFID_Request(PICC_REQIDL, str); 

  //Anti-collision, return tag serial number 4 bytes
  status = myRFID.AddicoreRFID_Anticoll(str);
      if (status == MI_OK)
  {
          serialClear();
          Serial.print(str[0], HEX);
           // Serial.print(" , ");
          Serial.print(str[1],HEX);
           // Serial.print(" , ");
          Serial.print(str[2],HEX);
           // Serial.print(" , ");
          Serial.print(str[3],HEX);
          Serial.println();
        digitalWrite(4, HIGH);
        digitalWrite(3, HIGH);
        delay(20);
        digitalWrite(3, LOW);
        nextState = inputListener;
      }
      break;
      
      
     case inputListener:
      if(Serial.available() > 0) {
                Serial.flush();
                digitalWrite(4, LOW);
                tic = 0;
                //idle value
                // read the incoming byte from pi:
                recieved = Serial.read();
                if(recieved == '0'){
                  Serial.println(recieved);
                  nextState = outputGreen;
                }
                if(recieved == '1'){
                  Serial.println(recieved);
                  nextState = outputRed;
                }
                serialClear();
                
        }
        else {
          nextState = timeout;
        }
        break;

       case outputRed:
        digitalWrite(7, HIGH);
        for(int i = 0; i<10; i++){
          digitalWrite(3, HIGH);
          delay(10);
          digitalWrite(3, LOW);
          delay(10);
        }
        delay(1000);
        digitalWrite(7, LOW);
        digitalWrite(4, HIGH);
        delay(6000);
        nextState = inputRfid;
        break;

       case outputGreen:
        digitalWrite(8, HIGH);
        digitalWrite(3, HIGH);
        delay(50);
        digitalWrite(3, LOW);
        delay(50);
        digitalWrite(3, HIGH);
        delay(50);
        digitalWrite(3, LOW);
        delay(1000);
        digitalWrite(8, LOW);
        digitalWrite(4, HIGH);
        delay(6000);
        nextState = inputRfid;
        break;
        
       case timeout:
        delay(100);
        tic += 1;
        nextState = inputListener;
        if(tic == 40){
          nextState = inputRfid;
          digitalWrite(4, LOW);
          tic = 0;
        }
        break;
  }
  currentState = nextState;
}

void serialClear(){
  while(Serial.available()>0) {
    char waste = Serial.read();
  }
}   
