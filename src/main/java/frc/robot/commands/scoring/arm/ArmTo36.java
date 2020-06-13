// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.commands.scoring.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.DriveForDistance;

/**
 *
 */
public class ArmTo36 extends CommandGroup {

    public ArmTo36() {
        addSequential(new DriveForDistance(-5.0/12));
        addSequential(new ArmToAngle(37));
    }

    @Override
    protected void initialize() {
        Robot.logInitialize(this);
    }

    @Override
    protected void end() {
        Robot.logEnd(this);
    }
}