#ifndef CONSTANTS
#define CONSTANTS

///pins
#define	RIGHT_BACK_LEG_PIN 6
#define RIGHT_BACK_HIP_PIN 5
    
#define RIGHT_FRONT_LEG_PIN 8
#define RIGHT_FRONT_HIP_PIN 7
  
#define LEFT_BACK_LEG_PIN 10
#define LEFT_BACK_HIP_PIN 9
    
#define LEFT_FRONT_LEG_PIN 12
#define LEFT_FRONT_HIP_PIN 11

#define LED_PIN           3

//initial position
#define LEFT_BACK_HIP_INIT_POS 87
#define LEFT_BACK_LEG_INIT_POS 90

#define LEFT_FRONT_HIP_INIT_POS 90
#define LEFT_FRONT_LEG_INIT_POS 90

#define RIGHT_BACK_HIP_INIT_POS 96
#define RIGHT_BACK_LEG_INIT_POS 90

#define RIGHT_FRONT_HIP_INIT_POS 76
#define RIGHT_FRONT_LEG_INIT_POS 90

//movement values
#define LEG_MOVEMENT_AMPLITUDE 		25
#define HIP_MOVEMENT_AMPLITUDE      15
#define HIP_MOVEMENT_AMPLITUDE_LONG 25

//commands
#define BAUD_RATE 9600
#define COMMAND_FORWARD		'f'
#define COMMAND_BACKWARD	'b'
#define COMMAND_LEFT		'l'
#define COMMAND_RIGHT		'r'
#define COMMAND_LED_ON    'o'
#define COMMAND_LED_OFF    'v'


#endif
