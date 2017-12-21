#ifndef SERVO_MOTOR_PCA_9685_H
#define SERVO_MOTOR_PCA_9685_H
/*
 * this class is to make easy 
 * to switch between normal servo control
 * and TLC controlled servo motor
 */

#include <Wire.h>
#include <Adafruit_PWMServoDriver.h>

typedef struct S_SERVO_MOTOR
{
  private:
    int angle;
    int pin;
    Adafruit_PWMServoDriver pwm;
  public:
  void attach(int pin_)
  {
     pin= pin_;
     pwm = Adafruit_PWMServoDriver();
     pwm.begin();
     pwm.setPWMFreq(FREQUENCY);    
  } 

  int pulseWidth(int angle)
  {
    int pulse_wide, analog_value;
    pulse_wide   = map(angle, 0, 180, MIN_PULSE_WIDTH, MAX_PULSE_WIDTH);
    analog_value = int(float(pulse_wide) / 1000000 * FREQUENCY * 4096);
    return analog_value;
  }
  
  int  read()
  {
    return angle;
  }
  void write(int angle_)
  {
    angle = angle_;
    pwm.setPWM(pin, 0, pulseWidth(angle));
  }
 }ServoMotor;

#endif
