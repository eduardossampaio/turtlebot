#include "Tlc5940.h"
#include "tlc_servos.h"
void setup()
{
  tlc_initServos();  
  pinMode(7,OUTPUT);
}
void loop()
{
  int i;
  
  for(int j=0;j<180;j+=10){
    for (i = 1;i <= 5; i++) {    
      tlc_setServo(i, j);
      Tlc.update();
      delay(50);
      if(j<=90){
        digitalWrite(7,HIGH);
      }else{
        digitalWrite(7,LOW);
      }
    }    
    
  }
  
}
