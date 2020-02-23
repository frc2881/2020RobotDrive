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

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Intake.RollerDirection;
//fix pathway

/**
 *
 */
public class IntakeFor7Inches extends Command {
    private double setpoint;
    private PIDController straightPID;
    //using the Ziegler-Nichols PID Control Tuning method, we find the proper numbers for the PID loop.
    private static final double Kc = 0.3;
    private static final double Pc = 0.47125;  // period of oscillation (found from average devided by 1/8 of a second(slow mo' camera))
    private static final double P = 0.6 * Kc; 
    private static final double I = 2 * P * 0.05 / Pc;
    private static final double D = 0.125 * P * Pc / 0.05;

    public IntakeFor7Inches() {
        //requires(Robot.intake);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        setpoint = Robot.intake.getIntakeMainEncoderPosition() + 7.125;
        straightPID = new PIDController(P, I * 0.1, D * 0.1);
        straightPID.setSetpoint(setpoint);
        straightPID.setTolerance(.5);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
       //System.out.println("IntakeFor7Inches executing");
       double value = straightPID.calculate(Robot.intake.getIntakeMainEncoderPosition());
       // Sets the minimum and maximum speed of the robot during the command 
       if (value > 0.2) {
           value = 0.2;
       } else if (value < -0.2) {
           value = -0.2;
       } else if (value > 0 && value < 0.05){
           value = 0.05;
       } else if (value < 0 && value > -0.05){
           value = -0.05;
       }
       if(straightPID.atSetpoint()){
           value = 0;
       }
       System.out.println("Value: " + value);
       System.out.println("Setpoint: " + setpoint);
       System.out.println("Current Position: " + Robot.intake.getIntakeMainEncoderPosition());

        Robot.intake.intakeMain(value, RollerDirection.INTAKE);
        // sequential order
        // rollers, intakeLeft/Right, intakeParallel
        // Robot.intake.intakeRightLeft(speed, RollerDirection.INTAKE);
        // Robot.intake.intakeParallelBand(speed, RollerDirection.INTAKE);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        //return straightPID.atSetpoint();
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void interrupted() {
        Robot.intake.intakeMain(0, RollerDirection.INTAKE);
        end();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void end() {
        Robot.intake.intakeMain(0, RollerDirection.INTAKE);
    }

}