#include <Servo.h>

#include "constants.h"
#include "Leg.h"
#include "Robot.h"

Robot turtlebot;

void setup() 
{
    turtlebot.initialize();   
    Serial.begin(BAUD_RATE);
    delay(3000);
    Serial.println("connected");
}

void loop() 
{
  	if(Serial.available()){
  		Serial.println("Command received");
  		char command = Serial.read();

  		switch(command){
  			case COMMAND_FORWARD:
  				Serial.println("moving forward");
  				turtlebot.forward();
  				break;
  			case COMMAND_BACKWARD:
  				Serial.println("moving backward");
  				turtlebot.backward();
  				break;
  			case COMMAND_LEFT:
  				Serial.println("moving left");
  				turtlebot.left();
  				break;
  			case COMMAND_RIGHT:
  				Serial.println("moving right");
  				turtlebot.right();
  				break;
        case COMMAND_LED_ON:
          Serial.println("LED ON");
          turtlebot.led_on();
          break;
        case COMMAND_LED_OFF:
          Serial.println("LED OFF");
          turtlebot.led_off();          
          break;
  			default:
  			Serial.println("unkown command");

  		}

  	}
    
    //delay(500);
   
  
}
