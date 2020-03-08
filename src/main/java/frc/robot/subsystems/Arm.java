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

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.commands.scoring.arm.ArmControl;
import frc.robot.utils.DistancePerPulse;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class Arm extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    private static final double highestGearTeethNumber = 32;
    private static final double lowestGearTeethNumber = -16;
    private static final double encoderCountsPerRevolution = 1;
    private static final double wheelDiameter = 0.5 / Math.PI;
    // Lead screw 0.5 inch travel per turn
    // 1 rev of motor = half rev of lead screw
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private CANSparkMax armMotor;
    private CANEncoder armEncoder;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private DoubleSupplier armPosition;
    private static double distancePerPulse;
    private static double beginningPosition;

    private double[][] angleToDistanceValues = { { 12.0289, 67 }, { 11.6955, 65 }, { 11.1907, 62 }, { 10.6812, 59 },
            { 10.1683, 56 }, { 9.6532, 53 }, { 9.1374, 50 }, { 8.6221, 47 }, { 8.1087, 44 }, { 7.5986, 41 },
            { 7.0932, 38 }, { 6.5942, 35 }, { 6.103, 32 }, { 5.6214, 29 }, { 5.151, 26 }, { 4.6937, 23 },
            { 4.2514, 20 }, { 3.8262, 17 }, { 3.42, 14 }, { 3.0351, 11 }, { 2.6693, 8 }, { 2.4984, 5 }, { 2.1903, 2 },
            { 2.0012, 0 } }; // first column is linear heights, second is angles
                             // https://docs.google.com/spreadsheets/d/1p0LHvVqUeNsd2AdovnEjmvRz7rfX7AGc-mqiHdR05lM/edit#gid=0

    public Arm() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        //armMotor = new CANSparkMax(12, MotorType.kBrushless);
        armMotor.setInverted(false);
        armMotor.setIdleMode(IdleMode.kBrake);

        armEncoder = armMotor.getEncoder();
        distancePerPulse = DistancePerPulse.get(highestGearTeethNumber, lowestGearTeethNumber,
                encoderCountsPerRevolution, wheelDiameter);
        beginningPosition = armEncoder.getPosition() * distancePerPulse - 2.0012;
        armPosition = () -> armEncoder.getPosition() * distancePerPulse;// - beginningPosition;
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        setDefaultCommand(new ArmControl());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Angle", this::getArmAngle, null);
        builder.addDoubleProperty("Height", this::getArmPosition, null);
    }

    public double toHeightInches(double angle) {
        return 2 + 0.0781 * angle + 1.99e-3 * Math.pow(angle, 2) - 1.38e-5 * Math.pow(angle, 3);// taken from
                                                                                                // https://docs.google.com/spreadsheets/d/1p0LHvVqUeNsd2AdovnEjmvRz7rfX7AGc-mqiHdR05lM/edit#gid=0
    }

    public double toAngleDegrees(double height) {
        return -21.8 + 12.8 * height - 0.851 * Math.pow(height, 2) + 0.0333 * Math.pow(height, 3);// taken from
                                                                                                  // https://docs.google.com/spreadsheets/d/1p0LHvVqUeNsd2AdovnEjmvRz7rfX7AGc-mqiHdR05lM/edit#gid=0
    }

    public void setArmSpeed(double speed) {
        if (Math.abs(speed) < 0.05) {
            armMotor.set(0);
        } else {
            armMotor.set(speed);
        }
    }

    public double getArmPosition() {
        if (armPosition.getAsDouble() - beginningPosition < 2.0012) {
            beginningPosition = armPosition.getAsDouble() - 2.0012;
            return 2.0012;
        } else {
            return armPosition.getAsDouble() - beginningPosition;
        }
    }

    public double getArmAngle() {
        return toAngleDegrees(getArmPosition());
    }
}
