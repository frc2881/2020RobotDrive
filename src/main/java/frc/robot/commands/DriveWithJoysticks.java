// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.GenericHID;

/**
 *
 */
public class DriveWithJoysticks extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveWithJoysticks() {

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        requires(Robot.drive);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.drive.setDeadband(0.2);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (!Robot.drive.getUseSplitArcade()){
        double left = -Robot.oi.getDriverLeftY();
        double right = -Robot.oi.getDriverRightY();
        double triggerLeft = Robot.oi.getDriverTriggerLeft();
        double triggerRight = Robot.oi.getDriverTriggerRight();
        Robot.drive.tankDrive(left, right);
        Robot.drive.hDrive(triggerLeft, triggerRight);
        } else {
            double xSpeed = (Robot.oi.getDriverLeftY());
            double zRotation = (Robot.oi.getDriverRightX());
            double triggerLeft = (Robot.oi.getDriverTriggerLeft());
            double triggerRight = (Robot.oi.getDriverTriggerRight());
            Robot.drive.arcadeDrive(xSpeed, zRotation);
            Robot.drive.hDrive(triggerLeft, triggerRight);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
