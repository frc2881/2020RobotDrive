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
import frc.robot.Robot;
import frc.robot.subsystems.Intake.RollerDirection;
//fix pathway

/**
 *
 */
public class IntakeParallel extends Command {
    /*  - rotate intake wheels (4?)
            - 1 motor, inverted directions
        - rotate perpendicular bands (3)  
            - 2 motors, 2 directions
        - rotate parallel bands (2)
            - 1 motor, inverted directions*/
            private static final double speed = 0.35;
        //change to more appropriate speed

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public IntakeParallel() {
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        requires(Robot.intake);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        //sequential order
        //rollers, intakeLeft/Right, intakeParallel
    Robot.intake.intakeMain(speed, RollerDirection.INTAKE);
    //Robot.intake
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return !Robot.intake.isIntakeFinished();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.flywheel.intakeFlywheel(0, RollerDirection.INTAKE);
        Robot.intake.intakeRightLeft(0, RollerDirection.INTAKE);
        Robot.intake.intakeMain(0, RollerDirection.INTAKE);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
