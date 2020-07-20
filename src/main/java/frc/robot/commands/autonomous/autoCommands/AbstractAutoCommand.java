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

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;

/**
 *
 */
public class AbstractAutoCommand extends CommandGroup {

    public AbstractAutoCommand() {
        super();
    }

    public AbstractAutoCommand(String name) {
        super(name);
    }

    @Override
    protected void initialize() {
        Robot.log(getName() + " has started");
    }

    @Override
    protected final void end() {
        Robot.log(getName() + " has ended");
    }
}
