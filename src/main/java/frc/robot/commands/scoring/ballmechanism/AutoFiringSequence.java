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

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.subsystems.BallStorage.RollerDirection;


/**
 *
 */
public class AutoFiringSequence extends CommandGroup {

    public AutoFiringSequence() {

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
    
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.logInitialize(this);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double time = timeSinceInitialized();
        Robot.ballStorage.intakeMain(1, RollerDirection.INTAKE);
        if (time > .40) { //also worked well at .25
            Robot.ballStorage.armAlign(1, 1);
        }
        else if (time > 0.05) { //also worked well at .10
            Robot.ballStorage.armAlign(1, 0);
        }  
    }


    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void interrupted() {
        Robot.logInterrupted(this);
        Robot.ballStorage.armAlign(0, 0);
        Robot.ballStorage.intakeMain(0, RollerDirection.INTAKE);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void end() {
        Robot.logEnd(this);
    }
}
