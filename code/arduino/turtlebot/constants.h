#ifndef CONSTANTS
#define CONSTANTS

//doll head
#define HAVE_DOLL_HEAD //comment if yout robot don't have dool's head (or another rotable part)

#ifdef HAVE_DOLL_HEAD
#define DOLL_HEAD_PIN 4

#define DOLL_HEAD_INIT_ANGLE 90

#endif

#define HAVE_PCA9685 //uncomment if are using PCA9685

#ifdef HAVE_PCA9685

//#define MIN_PULSE_WIDTH       650
//#define MAX_PULSE_WIDTH       2350
#define DEFAULT_PULSE_WIDTH   1500
#define FREQUENCY             50

#endif

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
#define LEG_MOVEMENT_AMPLITUDE 		  25
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

#ifdef HAVE_DOLL_HEAD
#define COMMAND_DOLL_RIGHT    'q'
#define COMMAND_DOLL_LEFT    'e'
#endif

//resposnses
#define RESPONSE_UNKNOW		'U'
#define RESPONSE_FORWARD	'F'
#define RESPONSE_BACKWARD	'B'
#define RESPONSE_LEFT		  'L'
#define RESPONSE_RIGHT		'R'
#define RESPONSE_LED_ON    	'O'
#define RESPONSE_LED_OFF    'V'

#ifdef HAVE_DOLL_HEAD
#define RESPONSE_DOLL_RIGHT   'Q'
#define RESPONSE_DOLL_LEFT    'E'
#endif

#endif //CONSTANTS_H



