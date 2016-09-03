#include <Servo.h>
#include "Tlc5940.h"
#include "tlc_servos.h"

#include "constants.h"
#include "ServoMotor.h"
#include "Leg.h"
#include "Robot.h"



Robot turtlebot;

void setup() 
{
    turtlebot.initialize();   
    Serial.begin(BAUD_RATE);
    delay(3000);   
}

void respond(char response){
  Serial.println(response);
}

void loop() 
{
  	if(Serial.available()){
  		
  		char command = Serial.read();

  		switch(command){
  			case COMMAND_FORWARD:  		
  				turtlebot.forward();
          respond(RESPONSE_FORWARD);
  				break;
  			case COMMAND_BACKWARD:  			
  				turtlebot.backward();
          respond(RESPONSE_BACKWARD);
  				break;
  			case COMMAND_LEFT:  				
  				turtlebot.left();
          respond(RESPONSE_LEFT);
  				break;
  			case COMMAND_RIGHT:  				
  				turtlebot.right();
          respond(RESPONSE_RIGHT);
  				break;
        case COMMAND_LED_ON:          
          turtlebot.led_on();
           respond(RESPONSE_LED_ON);
          break;
        case COMMAND_LED_OFF:          
          turtlebot.led_off();          
          respond(RESPONSE_LED_OFF);
          break;
#ifdef HAVE_DOLL_HEAD
        case COMMAND_DOLL_RIGHT:          
          turtlebot.doll_head_right();          
          respond(RESPONSE_DOLL_RIGHT);
          break;
        case COMMAND_DOLL_LEFT:          
          turtlebot.doll_head_left();          
          respond(RESPONSE_DOLL_LEFT);
          break;
#endif
  			default:
  			  break;

  		}


  	}
            
}
