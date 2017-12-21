#ifndef SERVO_MOTOR_DEFAULT_H
#define SERVO_MOTOR_DEFAULT_H
/*
 * this class is to make easy 
 * to switch between normal servo control
 * and TLC controlled servo motor
 */
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


#endif
