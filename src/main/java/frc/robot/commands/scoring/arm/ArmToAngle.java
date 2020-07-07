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

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.utils.ArmAmpMonitor;

/**
 *
 */
public class ArmToAngle extends Command {
    private double angle;
    private boolean monitoringAmps;
    private ArmAmpMonitor ampMonitor = new ArmAmpMonitor(25, 5, () -> Robot.arm.getArmCurrent(), () -> Robot.arm.getArmVelocity());

    public ArmToAngle(double angle) {
        requires(Robot.arm);
        this.angle = angle;
        monitoringAmps = false;

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.logInitialize(this);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        // Calls to the subsystem to update the angle if controller value has changed
        ampMonitor.checkTriggered();

        double time = timeSinceInitialized();
        double speed;
        double difference = angle - Robot.arm.getArmAngle();
        double multiplier = difference > 0 ? 0.65 : 0.5; //0.6: smooth but a little show; 0.65 faster but jitters a little

        //to adjust ramp rate as it slows: adjust the number that difference is compared to and divided by in the 3rd else statement
        //to adjust deadband change the last number in isFinished()
        //to adjust speed when going up/down: change multiplier

        if (Math.abs(difference) <= 0.6) {
            speed = 0;
        } else if (time < 1) {
            speed = Math.copySign(time * (multiplier - 0.1) + 0.1, difference);
        } else if (Math.abs(difference) < 5) {
            speed = difference / 5 * multiplier;
        } else if (Math.abs(difference) >= 5) {
            speed = Math.copySign(multiplier, difference);
        } else {
            speed = 0;
        }

        if (!monitoringAmps && timeSinceInitialized() > 0.2) {
            Robot.log("ArmToAngle Amp monitoring");
            ampMonitor.reset();
            monitoringAmps = true;
        } else if (monitoringAmps && ampMonitor.isTriggered()) {
            if (Robot.arm.getArmVelocity() < 0 || !ampMonitor.armGoingUp()) {
                Robot.arm.resetArmEncoder(false);
            }
            speed = 0;
            Robot.log("Arm Current Limit Exceeded");
        }

        Robot.log("remaining distance: " + difference);
        Robot.log("speed: " + speed);
        Robot.arm.setArmSpeed(-speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        // asking the pid loop have we reached our position
        return Math.abs(angle - Robot.arm.getArmAngle()) <= 0.6;
    }

    @Override
    protected void interrupted() {
        Robot.logInterrupted(this);
        // call the drive subsystem to make sure the PID loop is disabled
        Robot.arm.setArmSpeed(0);
        monitoringAmps = false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.logEnd(this);
        // call the drive subsystem to make sure the PID loop is disabled
        Robot.arm.setArmSpeed(0);
        monitoringAmps = false;
    }

}