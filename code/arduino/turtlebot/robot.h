#ifndef ROBOT_H
#define ROBOT_H

#if defined(ARDUINO) && ARDUINO >= 100
      #include "Arduino.h"
    #else
      #include "WProgram.h"
    #endif
#include "Leg.h"

typedef struct s_Robot{
  
private:
    Leg left_front;
    Leg left_back;
    Leg right_front;
    Leg right_back;
  
public:
  
  void initialize()
  {
    
    right_front.attach_pins(RIGHT_FRONT_HIP_PIN,RIGHT_FRONT_LEG_PIN);
    right_front.set_direction(DIR_RIGHT);
    right_front.set_place(PLACE_FRONT);

    right_back.attach_pins(RIGHT_BACK_HIP_PIN,RIGHT_BACK_LEG_PIN);
    right_back.set_direction(DIR_RIGHT);
    right_back.set_place(PLACE_BACK);    
  
    left_front.attach_pins(LEFT_FRONT_HIP_PIN,LEFT_FRONT_LEG_PIN);    
    left_front.set_direction(DIR_LEFT);
    left_front.set_place(PLACE_FRONT);
    
    left_back.attach_pins(LEFT_BACK_HIP_PIN,LEFT_BACK_LEG_PIN);
    left_back.set_direction(DIR_LEFT);
    left_back.set_place(PLACE_BACK);
    
    delay(300);
    
    left_front.init_pos(LEFT_FRONT_HIP_INIT_POS,LEFT_FRONT_LEG_INIT_POS);
    left_back.init_pos(LEFT_BACK_HIP_INIT_POS,LEFT_BACK_LEG_INIT_POS);
  
    right_front.init_pos(RIGHT_FRONT_HIP_INIT_POS,RIGHT_FRONT_LEG_INIT_POS);
    right_back.init_pos(RIGHT_BACK_HIP_INIT_POS,RIGHT_BACK_LEG_INIT_POS);
   
  }

  void change_height(int new_height)
  {
    
  }

  void idle()
  {
    delay(100);
  }

  void forward() 
  {            
    
    // step 1
    right_front.up();
    idle();
    right_front.forward();
    idle();    
    left_front.backward();
    right_front.leg_home();    
  
    //step 2
    right_back.up();
    idle();
    right_back.forward();
    idle();
    left_back.backward();
    right_back.leg_home();
     
    //step 3
    left_front.up();
    idle();
    left_front.forward();
    idle();
    right_front.backward();
    left_front.leg_home();

    //step 4
    left_back.up();
    idle();
    left_back.forward();
    idle();
    right_back.backward();
    left_back.leg_home();
  }

  void backward()
  {
    //step 1
    right_back.up();
    idle();
    right_back.backward();
    idle();
    left_back.forward();
    right_back.leg_home();

    //step 2
    left_front.up();
    idle();
    left_front.backward();
    idle();
    right_front.forward();
    left_front.leg_home();

    //step 3
    left_back.up();
    idle();
    left_back.backward();
    idle();
    right_back.forward();
    left_back.leg_home();

    //step 4
    right_front.up();
    idle();
    right_front.backward();
    idle();
    left_front.forward();
    right_front.leg_home();
  }

  void left()
  {
    right_front.up();
    left_back.up();
    idle();
    right_back.backward();
    left_front.forward();
    idle();
    right_front.leg_home();
    left_back.leg_home();

    right_back.up();
    right_back.hip_home();
    right_back.leg_home();

    idle();

    left_front.up();
    left_front.hip_home();
    left_front.leg_home();    
  }

  void right()
  {
    
    right_back.up();
    left_front.up();
    idle();
    right_front.forward();
    left_back.backward();
    idle();
    right_back.leg_home();
    left_front.leg_home();

    right_front.up();
    right_front.hip_home();
    right_front.leg_home();

    idle();

    left_back.up();
    left_back.hip_home();
    left_back.leg_home();  
    /*
    right_front.up();
    left_back.up();
    idle();
    right_back.forward();
    left_front.backward();
    idle();
    right_front.leg_home();
    left_back.leg_home();

    right_back.up();
    right_back.hip_home();
    right_back.leg_home();

    idle();

    left_front.up();
    left_front.hip_home();
    left_front.leg_home();    
    */

  }


  void test()
  {    
    right_front.forward();
    left_front.forward();    
    delay(1000) ;
    right_front.backward();    
    left_front.backward();
    delay(1000) ;
   
   
  }

}Robot;

#endif

