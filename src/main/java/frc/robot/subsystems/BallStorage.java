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

import java.util.function.DoubleSupplier;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.scoring.ballmechanism.ControlFeeder;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.commands.scoring.ballmechanism.*;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class BallStorage extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
    public enum RollerDirection {
        INTAKE, EJECT
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private CANSparkMax intakeMain;

    private CANSparkMax intakeLeft;
    private CANSparkMax intakeRight;
    private CANEncoder intakeMainEncoder;
    private DoubleSupplier intakeMainEncoderPosition;

    private static final double highestGearTeethNumber = 7.0;
    private static final double lowestGearTeethNumber = 1.0;
    private static final double encoderCountsPerRevolution = 42;
    private static final double wheelDiameter = 1.5;


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public BallStorage() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        intakeLeft = new CANSparkMax(9, MotorType.kBrushless); // Align Arm Left

        intakeLeft.setInverted(true);
        intakeLeft.setIdleMode(IdleMode.kBrake);

        intakeRight = new CANSparkMax(8, MotorType.kBrushless); // Align Arm Right

        intakeRight.setInverted(false);
        intakeRight.setIdleMode(IdleMode.kBrake);
        
        intakeMain = new CANSparkMax(7, MotorType.kBrushless); // Tube Intake

        intakeMain.setInverted(false);
        intakeMain.setIdleMode(IdleMode.kBrake);


        // 42 ticks per rotation, 7:1 gearbox - nine revolutions for one wheel
        // revolution, inch and a half diameter, 3/16 inch belt
        double ticksPerRevolution = encoderCountsPerRevolution;
        double gearRatio = lowestGearTeethNumber / highestGearTeethNumber;
        double wheelCircumference = wheelDiameter * Math.PI;
        double distancePerPulse = wheelCircumference / ticksPerRevolution / gearRatio;
        intakeMainEncoder = intakeMain.getEncoder();
        intakeMainEncoderPosition = () -> intakeMainEncoder.getPosition() * -distancePerPulse;

    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Storage Bus Voltage", () -> intakeMain.getBusVoltage(), null);
        builder.addDoubleProperty("Storage Output Current", () -> intakeMain.getOutputCurrent(), null);
        builder.addDoubleProperty("Storage Sticky Faults", () -> intakeMain.getStickyFaults(), null);

        builder.addDoubleProperty("Left Centering Bus Voltage", () -> intakeLeft.getBusVoltage(), null);
        builder.addDoubleProperty("Left Centering Output Current", () -> intakeLeft.getOutputCurrent(), null);
        builder.addDoubleProperty("Left Centering Sticky Faults", () -> intakeLeft.getStickyFaults(), null);

        builder.addDoubleProperty("Right Centering Bus Voltage", () -> intakeRight.getBusVoltage(), null);
        builder.addDoubleProperty("Right Centering Output Current", () -> intakeRight.getOutputCurrent(), null);
        builder.addDoubleProperty("Right Centering Sticky Faults", () -> intakeRight.getStickyFaults(), null);
    }

    public void intakeMain(double speed, RollerDirection state) {
        if (state == RollerDirection.INTAKE) {
            intakeMain.set(-speed);
        } else {
            // EJECT
            intakeMain.set(speed);
        }
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

    public double getIntakeMainEncoderPosition() {
        return intakeMainEncoderPosition.getAsDouble();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new EjectStorage(0));

    }
}
