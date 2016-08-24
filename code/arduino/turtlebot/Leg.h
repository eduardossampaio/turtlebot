#ifndef LEG_H
#define LEG_H

#if defined(ARDUINO) && ARDUINO >= 100
      #include "Arduino.h"
    #else
      #include "WProgram.h"
    #endif
#include "Leg.h"

//direction of leg
#define DIR_LEFT    1
#define DIR_RIGHT   2
//place in robot
#define PLACE_FRONT 3
#define PLACE_BACK  4

//#define SMOOTH_STEP 2


/**
* The leg of robot, control the servo motors
* 
*/

typedef struct s_Leg{
  private:   
  public:
    Servo hip;
    Servo leg;
    int hip_angle;
    int hip_init_pos;
    int leg_angle;
    int leg_init_pos;
    int direction;
    int place;

  /*
  *  attach the servos to the pins
  */
  void attach_pins(int hip_pin,int leg_pin)
  {
    hip.attach(hip_pin);
    leg.attach(leg_pin);
  }

  void set_direction(int dir)
  {
    direction = dir; 
  }

  void set_place(int place_)
  {
    place = place_;
  }

  /**
  * put and set the servos to initial positions
  */
  void init_pos(int hip_angle_,int leg_angle_)
  {
    
    hip_angle = hip_angle_;
    leg_angle = leg_angle_;
    hip_init_pos = hip_angle_;
    leg_init_pos = leg_angle_;
    leg.write(leg_angle);
    hip.write(hip_angle);
      
  }

  void write_leg(int angle)
  {
    if(leg_angle < angle){
      for(int i=leg_angle; i<=angle; i+=5)
      {
        leg.write(i);
        delay(5);
      }
    }else{
      for(int i=leg_angle; i>=angle; i-=5)
      {
        leg.write(i);
        delay(5);
      }
    }
    leg_angle = angle;    
  }

  void write_hip(int angle)
  {
     if(hip_angle < angle){
      for(int i=hip_angle; i<=angle; i+=3)
      {
        hip.write(i);
        delay(5);
      }
    }else{
      for(int i=hip_angle; i>=angle; i-=3)
      {
        hip.write(i);
        delay(5);
      }
    }     
    hip_angle = angle;    
  }
 
  void forward()
  {
      
      if( place == PLACE_FRONT){
        if(direction == DIR_LEFT){
          write_hip( hip_init_pos - HIP_MOVEMENT_AMPLITUDE_LONG);
        }else{
          write_hip(hip_init_pos + HIP_MOVEMENT_AMPLITUDE_LONG);
        }
      }
      else{
        if(direction == DIR_LEFT){
          write_hip(hip_init_pos - HIP_MOVEMENT_AMPLITUDE);
        }else{
          write_hip(hip_init_pos + HIP_MOVEMENT_AMPLITUDE);
        }
      }
      
  }
  void backward()
  {
    
    if( place == PLACE_FRONT){
        if(direction == DIR_LEFT)
          write_hip(hip_init_pos + HIP_MOVEMENT_AMPLITUDE);
        else
          write_hip(hip_init_pos - HIP_MOVEMENT_AMPLITUDE);
      }else{
        if(direction == DIR_LEFT)
          write_hip(hip_init_pos + HIP_MOVEMENT_AMPLITUDE_LONG);
        else
          write_hip(hip_init_pos - HIP_MOVEMENT_AMPLITUDE_LONG);
      }
      
  }
  void up()
  {
    if(direction == DIR_LEFT)
      write_leg(leg_init_pos + LEG_MOVEMENT_AMPLITUDE);
    else
      write_leg(leg_init_pos - LEG_MOVEMENT_AMPLITUDE);
  }
  void down()
  {
     if(direction == DIR_LEFT)
      write_leg(leg_init_pos - LEG_MOVEMENT_AMPLITUDE);
    else
      write_leg(leg_init_pos + LEG_MOVEMENT_AMPLITUDE);
  }
  void leg_home()
  {
    write_leg(leg_init_pos);
  }
  void hip_home()
  {
    write_hip(hip_init_pos);
  }
  
  int get_leg_angle()
  {
    return leg_angle;
  }

  int get_hip_angle()
  {
    return hip_angle;
  }
  
  
  
}Leg;


#endif
