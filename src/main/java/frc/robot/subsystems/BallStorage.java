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
import frc.robot.utils.frc4048.Logging;

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
        setDefaultCommand(new AutoFiringSequence());

    }

    public Logging.LoggingContext loggingContext = new Logging.LoggingContext(Logging.Subsystems.BALLSTORAGE){

        @Override
        protected void addAll(){
            add("LEFT ALIGN: BusVoltage", intakeLeft.getBusVoltage());
            add("LEFT ALIGN: OutputCurrent", intakeLeft.getOutputCurrent());
            add("LEFT ALIGN: right FRONT: StickyFaults", intakeLeft.getStickyFaults());
            add("Right ALIGN: BusVoltage", intakeRight.getBusVoltage());
            add("Right ALIGN: OutputCurrent", intakeRight.getOutputCurrent());
            add("Right ALIGN: right FRONT: StickyFaults", intakeRight.getStickyFaults());
            add("TUBE: BusVoltage", intakeMain.getBusVoltage());
            add("TUBE: OutputCurrent", intakeMain.getOutputCurrent());
            add("TUBE: right FRONT: StickyFaults", intakeMain.getStickyFaults());
        }
    };

}
