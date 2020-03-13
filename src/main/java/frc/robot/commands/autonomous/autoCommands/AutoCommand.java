// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.commands.autonomous.autoCommands;

import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.autonomous.AutonomousRobotPrep;
import frc.robot.commands.autonomous.AutonomousWatchDog;
import frc.robot.commands.autonomous.autoCommands.Enums.*;

/**
 *
 */
public class AutoCommand extends AbstractAutoCommand {

    public AutoCommand(AutoOptions auto, StartingPosition start, double waitTime) {


        addSequential(new WaitCommand(waitTime));
        addParallel(new AutonomousRobotPrep());
        if (auto == AutoOptions.SCORE) {
            addSequential(new OverrideAuto(start));
        }
        else {
            addSequential(new DriveForDistance(5));
        }
    }

    @Override
    protected void initialize() {
        super.initialize();
        new AutonomousWatchDog(this).start();//Makes sure that if the robot is in danger of falling over, autonomous gets canceled
    }
}
