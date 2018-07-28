package org.usfirst.frc.team5876.robot;

import edu.wpi.first.wpilibj.DriverStation;

public class AutoDriveForward extends AutoTemplate{
	
	 String gameData;
	
	public AutoDriveForward() {
		
		 gameData = DriverStation.getInstance().getGameSpecificMessage();
	}
	
    public void autonomousInitCode(ArtemooseBot Moose){
    	Moose.prepareForAuto();
    }
    public void autonomousPeriodicCode(ArtemooseBot Moose){
    	System.out.println("Baseline");
		 System.out.println("X=" + Moose.accel.getX() + ", Y=" + Moose.accel.getY() + ", Z=" + Moose.accel.getZ() + ", gyro=" + Moose.gyro.getAngle());
		 
		 boolean completed = Moose.driveForward(8,8); //14 ft within 5 seconds
		 if (completed == true) {
			Moose.robotDriveBase.arcadeDrive(0,0);
		 }
    }
	
}