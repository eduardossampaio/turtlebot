#include <Servo.h>

#include "constants.h"
#include "Leg.h"
#include "Robot.h"

Robot turtlebot;

void setup() 
{
    turtlebot.initialize();   
    Serial.begin(9600);
    delay(3000);
    
}

void loop() 
{
  
    turtlebot.forward();
    //delay(500);
   
  
}
