// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.commands.scoring.ballmechanism;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.Robot;
import frc.robot.subsystems.BallStorage.RollerDirection;
//fix pathway

/**
 *
 */
public class IntakeFor7Inches extends Command {
    private double setpoint;
    private PIDController straightPID;
    int ctr = 0;
    

    private static double beginningPosition;


    public IntakeFor7Inches() {
        requires(Robot.ballStorage);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.logInitialize(this);
        setpoint = Robot.ballStorage.getIntakeMainEncoderPosition() + 7;
        //Robot.log("Beginning storage encoder position: " + Robot.intake.getIntakeMainEncoderPosition());
        beginningPosition = Robot.ballStorage.getIntakeMainEncoderPosition();
        ctr = Robot.ballStorage.getPowerCells();
        //Robot.intake.setBallStorageRampRate(0.5);
    } 

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
       //System.out.println("IntakeFor7Inches executing");
       double time = timeSinceInitialized();
       double speed;
       double distance = Robot.ballStorage.getIntakeMainEncoderPosition() - beginningPosition;
       // Sets the minimum and maximum speed of the robot during the command 
       if (time < 1) {
           speed = time * 0.4;
       } else if (distance < 5) {
           speed = 0.4;
       } else if (distance < 6.5){
           speed = 0.2 + ((6.5 - distance) / 7.5);
       } else if (distance < 9){
           speed = 0.2;
       } else {
           speed = 0;
       }

       //System.out.println("Value: " + value);
       System.out.println("Setpoint: " + setpoint);
       System.out.println("Current Position: " + Robot.ballStorage.getIntakeMainEncoderPosition());

        Robot.ballStorage.intakeMain(speed, RollerDirection.INTAKE);
        // sequential order
        // rollers, intakeLeft/Right, intakeParallel
        // Robot.intake.intakeRightLeft(speed, RollerDirection.INTAKE);
        // Robot.intake.intakeParallelBand(speed, RollerDirection.INTAKE);
        if(timeSinceInitialized() > 0.5){
            //Robot.intake.setBallStorageRampRate(0);
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        double distance = Robot.ballStorage.getIntakeMainEncoderPosition() - beginningPosition;
        //return straightPID.atSetpoint();

        if (distance >= 9){
            return true;
        }
        else {
            return false;
        }
    }

    // Called once after isFinished returns true
    @Override
    protected void interrupted() {
        Robot.logInterrupted(this);
        Robot.ballStorage.intakeMain(0, RollerDirection.INTAKE);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void end() {
        Robot.logEnd(this);
        Robot.ballStorage.intakeMain(0, RollerDirection.INTAKE);
        Robot.ballStorage.powerCellCtr(ctr+=3);
        //Robot.log("Ending storage encoder position: " + Robot.intake.getIntakeMainEncoderPosition());
        //Robot.log("Storage distance traveled: " + (Robot.intake.getIntakeMainEncoderPosition() - beginningPosition));
    }

}