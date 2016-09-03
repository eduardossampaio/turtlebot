#ifndef SERVO_MOTOR_H
#define SERVO_MOTOR_H
/*
 * this class is to make easy 
 * to switch between normal servo control
 * and TLC controlled servo motor
 */

 //uncomment this if you using TLC 5940
 //if you are using TLC, don´t use the
 //pins D3,D9,D10,D11 and D13
 //#define USING_TLC_5940

//mormal servo control
#ifndef USING_TLC_5940

typedef struct S_SERVO_MOTOR
{
	private:
 		int angle;
 		Servo servo;
 	public:
	void attach(int pin)
 	{
 		servo.attach(pin);
 	} 

 	int  read()
 	{
 		return angle;
 	}
 	void write(int angle_)
 	{
 		angle = angle_;
 		servo.write(angle);
 	}
 }ServoMotor;

#else


typedef struct S_SERVO_MOTOR
{
	private:
 		int pin;
 		int angle;
 	public:
	void attach(int pin_)
 	{
 		pin = pin_; 		
 	} 

 	int  read()
 	{
 		return angle;
 	}
 	void write(int angle_)
 	{
 		angle = angle_;
 		tlc_setServo(pin, angle);
 	}
 }ServoMotor;


#endif


#endif
