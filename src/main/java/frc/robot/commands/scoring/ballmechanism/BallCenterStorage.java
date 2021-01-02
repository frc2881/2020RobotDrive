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
import frc.robot.commands.background.wait.WaitForever;
import frc.robot.subsystems.BallStorage.Alignment;
import frc.robot.subsystems.BallStorage.Direction;



/**
 *
 */
public class BallCenterStorage extends CommandGroup {

    double speed;

    //power cell alignment: state, either RIGHT or LEFT, and state1, either CENTER or EJECT
    public BallCenterStorage(Alignment state, Direction state1) {

        requires(Robot.ballStorage);

        if(Robot.ballStorage.getPowerCells() <= 10){
        addSequential(new ArmAligningControl(state, state1));
        addSequential(new IntakeFor7Inches());}
        addSequential(new WaitForever());

        //currently only stores 3 power cells

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    
}