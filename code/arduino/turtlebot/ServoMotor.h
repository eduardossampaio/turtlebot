#ifndef SERVO_MOTOR_H
#define SERVO_MOTOR_H

#ifdef HAVE_PCA9685
  #include "ServoMotorPCA9685.h"
#else
  #include "ServoMotorDefault.h"
#endif

#endif
