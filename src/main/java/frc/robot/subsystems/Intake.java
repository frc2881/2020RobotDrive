// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.Rev2mDistanceSensor.Port;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class Intake extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
    public enum RollerDirection {
        INTAKE, EJECT
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private CANSparkMax flywheel;

    // private CANSparkMax IntakeFlywheel;
    private CANSparkMax intakeLeft;
    private CANSparkMax intakeRight;
    private CANSparkMax intakeMain;
    private Solenoid flywheelSolenoid;
    private Encoder colourWheelEncoder;
    private Spark angleAdjustment;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private Rev2mDistanceSensor leftSensor;
    private Rev2mDistanceSensor rightSensor;
    private Rev2mDistanceSensor middleSensor;

    public Intake() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        intakeLeft = new CANSparkMax(9, MotorType.kBrushless); // Align Arm Left

        intakeLeft.restoreFactoryDefaults();
        intakeLeft.setInverted(false);
        intakeLeft.setIdleMode(IdleMode.kBrake);

        intakeRight = new CANSparkMax(8, MotorType.kBrushless); // Align Arm Right

        intakeRight.restoreFactoryDefaults();
        intakeRight.setInverted(false);
        intakeRight.setIdleMode(IdleMode.kBrake);

        intakeMain = new CANSparkMax(7, MotorType.kBrushless); // Tube Intake

        intakeMain.restoreFactoryDefaults();
        intakeMain.setInverted(false);
        intakeMain.setIdleMode(IdleMode.kBrake);

        flywheel = new CANSparkMax(6, MotorType.kBrushless);
        flywheel.setInverted(false);
        flywheel.setIdleMode(IdleMode.kCoast);

        flywheelSolenoid = new Solenoid(0, 0);
        addChild("Flywheel Solenoid", flywheelSolenoid);

        colourWheelEncoder = new Encoder(0, 1, false, EncodingType.k4X);
        addChild("Colour Wheel Encoder", colourWheelEncoder);
        colourWheelEncoder.setDistancePerPulse(1.0);
        colourWheelEncoder.setPIDSourceType(PIDSourceType.kRate);

        angleAdjustment = new Spark(9);
        addChild("Angle Adjustment", angleAdjustment);
        angleAdjustment.setInverted(false);

        leftSensor = new Rev2mDistanceSensor(Port.kOnboard);
        leftSensor.setAutomaticMode(true);
        leftSensor.setEnabled(true);

        rightSensor = new Rev2mDistanceSensor(Port.kOnboard);
        rightSensor.setAutomaticMode(true);
        rightSensor.setEnabled(true);
    }

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    public void intakeFlywheel(double speed, RollerDirection state) {
        if (state == RollerDirection.INTAKE) {
            flywheel.set(-speed);
        } else {
            // EJECT
            flywheel.set(speed);
        }
    }

    public void flywheel(double speed) {
        flywheel.set(speed);
    }

    public boolean getIntakeFlywheel() {
        return (flywheel.get() > 0.05);
        // return if sufficient speed?
    }

    public void setFlywheel(double speed) {
        flywheel.set(speed);
    }

    public void intakeRightLeft(double speed, RollerDirection state) {
        if (state == RollerDirection.EJECT) {
            intakeLeft.set(0);
            intakeRight.set(0);
        } else {
            intakeLeft.set(speed);
            intakeRight.set(-speed);
            // don't need opposite speed
        }
    }

    public void intakeMain(double speed, RollerDirection state) {
        if (state == RollerDirection.INTAKE) {
            intakeMain.set(-speed);
        } else {
            // EJECT
            intakeMain.set(speed);
        }
    }

    public void intakeCell(double speed) {
        if (leftSensor.getRange() < 3){
            intakeLeft.set(speed);
            intakeRight.set(0);
        } else if (rightSensor.getRange() < 3) {
            intakeLeft.set(0);
            intakeRight.set(-speed);
            // don't need opposite speed
        } else if (middleSensor.getRange() < 6) {
            intakeLeft.set(0);
            intakeRight.set(0);
            intakeMain.set(speed);
        }
        else {
            intakeLeft.set(0);
            intakeRight.set(0);
            intakeMain.set(0);
        }
    }

    public boolean isIntakeFinished() {
        return middleSensor.getRange() < 6;
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }

    public void armAlign(double left, double right) { // positive values make cells go in, negative goes out
        /*
         * if(left>-1&&right>-1){ intakeLeft.set(0); intakeRight.set(0); } else
         * if(left>-1){ intakeLeft.set((left+1)/2); intakeRight.set(0); } else
         * if(right>-1){ intakeRight.set((right+1)/2); intakeLeft.set(0); } else{
         * intakeRight.set(0); intakeLeft.set(0); }
         */
        intakeLeft.set(left);
        intakeRight.set(right);
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}
