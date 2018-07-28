package org.usfirst.frc.team5876.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class ArtemooseBot {
	// The drive base of the robots
	  DifferentialDrive robotDriveBase;

	  // The motor controllers (will be passed in to the drive base)
	  SpeedController motorDriveLeftFront;
	  SpeedController motorDriveLeftBack;
	  SpeedControllerGroup left_motors;
	  SpeedController motorDriveRightFront;
	  SpeedController motorDriveRightBack;
	  SpeedControllerGroup right_motors;
	  
	  //additional motor controllers
	  SpeedController crabWheelsLeft;
	  SpeedController crabWheelsRight;
	  SpeedController pulley;

	  //accelerometer, gyro and encoder
	  ADXRS450_Gyro gyro;
	  BuiltInAccelerometer accel;
	  //Encoder encoder;

	  //timer
	  Timer timer;
	  
	public ArtemooseBot() {
		// set up individual drive motors
	    motorDriveLeftFront =     new VictorSP(0);
	    motorDriveLeftBack =      new VictorSP(1);
	
	    motorDriveRightFront =    new VictorSP(2);
	    motorDriveRightBack =     new VictorSP(3);
	
	    // set up drive motor groups and the drive base
	    left_motors =   new SpeedControllerGroup(motorDriveLeftFront, motorDriveLeftBack);
	    right_motors =  new SpeedControllerGroup(motorDriveRightFront, motorDriveRightBack);
	
	    robotDriveBase = new DifferentialDrive(left_motors, right_motors);
	    
	    //additional motors
	    crabWheelsLeft = new Spark(4);
	    crabWheelsRight = new Spark(5);
	    pulley = new Spark(6);
	  
	    accel = new BuiltInAccelerometer();
	
	  //  encoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	    
	    timer = new Timer();
	
	    gyro = new ADXRS450_Gyro();
	    gyro.calibrate();
	
	  //  CameraServer.getInstance().startAutomaticCapture();
	}
	
	void arcadeDrive(double xSpeed, double zRotation, boolean squareInput) {
		robotDriveBase.arcadeDrive(xSpeed, zRotation, squareInput);
	}
	
	void tankDrive(double leftSpeed, double rightSpeed) {
		robotDriveBase.tankDrive(leftSpeed, rightSpeed);
	}
	
	boolean driveForward(int time, double timeout) { //function to reach goal distance (inches)
		 // encoder.reset();
		  Timer timerDrive = new Timer();
		  timerDrive.start();
		//  encoder.setDistancePerPulse(18.85); //18.85 = distance of rotation 
		  
		  double timed = timerDrive.get(); //time travelled
//		 timerDrive.delay(8); 
		  if(timed < time ) { //allows amount travelled to reach goal distance 
			  robotDriveBase.arcadeDrive(0.5 , 0);  
			 // travel = encoder.getDistance();
			  System.out.println(timerDrive.get());
			  timed = timerDrive.get();
		  }
		  robotDriveBase.arcadeDrive(0,0);
		  if (timerDrive.get() <= timeout) {
			  return true; //completed within timeout limit
		  }
		  else {
			  return false; //oh no too slow
		  }
	  } //end void driveForward()
	  
	  boolean driveForwardWithGyro(int time, double timeout) { //function to reach goal distance (inches)
		 // encoder.reset();
		  Timer timerDrive = new Timer();
		  timerDrive.start();
		 // encoder.setDistancePerPulse(18.85); //18.85 = distance of rotation 
		  
		  double timed = timerDrive.get(); //amount travelled
		  
		  if(timed < time ) { //allows amount travelled to reach goal distance 
			  
			  double angle = gyro.getAngle();
				double Kp = 0.05;
				robotDriveBase.arcadeDrive(0.5, angle * Kp);
				
				
				Timer.delay(0.01);
				timed = timerDrive.get();
		  }
		  if (timerDrive.get() < timeout) {
			  return true; //completed within timeout limit
		  }
		  else {
			  return false; //oh no too slow
		  }
	  } //end void driveForwardWithGyro()
	  
	  boolean turn(int angle , double timeout) { //function to reach goal angle
		  gyro.reset();
		  Timer timerTurn = new Timer();
		  timerTurn.start();
		  
		  double turned = gyro.getAngle(); //amount turned
		  
		  if(turned < angle && timerTurn.get() < timeout) { 
		  robotDriveBase.arcadeDrive(0, 0.4);
		  turned = gyro.getAngle();
		  }
		  									//two situations where amount turned reaches goal angle
		  else if(turned > angle && timerTurn.get() < timeout) {
			  robotDriveBase.arcadeDrive(0,-0.4);
			  turned = gyro.getAngle();
		  }
		  if (timerTurn.get() < timeout) {
			  return true; //completed within timeout limit
		  }
		  else {
			  return false; //oh no too slow
		  }
	  } //end void turn()
	  
	  void crabWheelsIn(){
		  crabWheelsRight.set(0.5);
		  crabWheelsLeft.set(-0.5);
	  }
	  
	  void crabWheelsOut(){
		  crabWheelsRight.set(-0.5);
		  crabWheelsLeft.set(0.5);
	  }
	  
	  void crabWheelsStop(){
		  crabWheelsRight.set(0);
		  crabWheelsLeft.set(0);
	  }
	  
	  void pulleyForward(){
		  pulley.set(1);
	  }
	  
	  void pulleyBack(){
		  pulley.set(-1);
	  }
	
	  void pulleyStop(){
		  pulley.set(0);
	  }
	  
	void prepareForAuto() {
		gyro.reset();
		//encoder.reset();
	}

}