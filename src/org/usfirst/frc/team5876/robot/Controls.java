package org.usfirst.frc.team5876.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class Controls {

	//Joystick joystickLeft; // stick
	//Joystick joystickRight; // gamepad
	XboxController gamepadController; // gamepadController
	
	
	public Controls()
	{
		//joystickLeft = new Joystick(0);
		//joystickRight = new Joystick(1);
		gamepadController = new XboxController(0);
	}
	
	public void updateControls(ArtemooseBot Moose)
	{
		 //System.out.println("X=" + accel.getX() + ", Y=" + accel.getY() + ", Z=" + accel.getZ() + ", gyro=" + gyro.getAngle());

		    //check left joystick slow speed control override
//		    int pov = gamepadController.getPOV();
//		    //check right joystick slow speed control override
//		    //int povTurn = joystickRight.getPOV();
//
//		    float slow = 0.5f;
//		    float turn = 0, forward = 0;
//
//
//		    // check the thumb controllers on the joysticks in case we need to override
//		    // the joystick drive commands. (This is for slow mode.)
//		    if (pov != -1 || pov != -1) {
//		      // the forward/back thumb controller to check if it is at one of
//		      // 0 degrees, 315 degrees, or 45 degrees for slow forward
//		      // XOR
//		      // 180 degrees, 135 degrees, or 225 degrees for slow backwards
//		      switch (pov) {
//		        case 0:
//		        case 315:
//		        case 45:
//		          forward = -slow;
//		        break;
//
//		        case 180:
//		        case 135:
//		        case 225:
//		          forward = slow;
//		        break;
//		      }
//
//		      // the left / right thumb controller to check if it is pointing in one
//		      // of the left or right regions.
//		      switch (pov) {
//		        case 90:
//		        case 45:
//		        case 135:
//		          turn = -slow;
//		        break;
//
//		        case 270:
//		        case 225:
//		        case 315:
//		          turn = slow;
//		        break;
//		      }
//		      Moose.arcadeDrive(forward, turn, true);
		     // end if -- for slow movement inputs ... otherwise feed in normal joystick drive...
		    
		      //drive the robot as normal
		    
		    Moose.arcadeDrive(-(gamepadController.getRawAxis(1)), (gamepadController.getRawAxis(4)), true);
		     // end if/else -- for slow or normal drive

		    //End code for driving
		    
		    
		  if (gamepadController.getRawButton(6) == true){
			  Moose.pulleyForward();
		  }
		  
		  else if (gamepadController.getRawButton(5) == true){
			  Moose.pulleyBack();
		  }
		  
		  else {
			  Moose.pulleyStop();
		  }
		  
		  if (gamepadController.getRawAxis(2) > 0){
			  Moose.crabWheelsLeft.set(-(gamepadController.getRawAxis(2)));
			  Moose.crabWheelsRight.set((gamepadController.getRawAxis(2)));		  
		  }
		  
		  else if (gamepadController.getRawAxis(3) > 0){
			  Moose.crabWheelsLeft.set((gamepadController.getRawAxis(3)));
			  Moose.crabWheelsRight.set(-(gamepadController.getRawAxis(3)));
		  }
		  
		  else{
			  Moose.crabWheelsStop();
		  }
	}
	
	
}