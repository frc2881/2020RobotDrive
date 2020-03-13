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

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.commands.background.drive.DriveWithJoysticks;
import frc.robot.utils.DistancePerPulse;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class Drive extends Subsystem {

    //16 to 80, 30 to 50
    private static final double highestGearTeethNumber = 25;
    private static final double lowestGearTeethNumber = 3;
    private static final double wheelDiameter = 6;

    private CANSparkMax leftFront;
    private CANSparkMax leftRear;
    private CANSparkMax rightFront;
    private CANSparkMax rightRear;
    private DifferentialDrive differentialDrive1;
    private Spark spotlight;

    private CANEncoder leftEncoder;
    private CANEncoder rightEncoder;
    private DoubleSupplier drivePosition;
    private double distancePerPulse;
    // fix in RobotBuilder later

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private CANSparkMax hDrive;
    private boolean useSplitArcade;

    public Drive() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        leftFront = new CANSparkMax(1, MotorType.kBrushless);
        leftFront.setInverted(false);
        leftFront.setIdleMode(IdleMode.kBrake);

        leftRear = new CANSparkMax(2, MotorType.kBrushless);
        leftRear.setInverted(false);
        leftRear.setIdleMode(IdleMode.kBrake);

        setUseSplitArcade(true);

        // Set SlaveSpeedControllers to Follow MasterSpeedController
        leftRear.follow(leftFront);

        rightFront = new CANSparkMax(3, MotorType.kBrushless);
        rightFront.setInverted(false);
        rightFront.setIdleMode(IdleMode.kBrake);

        rightRear = new CANSparkMax(4, MotorType.kBrushless);
        rightRear.setInverted(false);
        rightRear.setIdleMode(IdleMode.kBrake);

        // Set SlaveSpeedControllers to Follow MasterSpeedController
        rightRear.follow(rightFront);

        differentialDrive1 = new DifferentialDrive(leftFront, rightFront);
        addChild("Differential Drive 1", differentialDrive1);
        differentialDrive1.setSafetyEnabled(true);
        differentialDrive1.setExpiration(0.1);
        differentialDrive1.setMaxOutput(1.0);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        hDrive = new CANSparkMax(5, MotorType.kBrushless);
        hDrive.setInverted(false);
        hDrive.setIdleMode(IdleMode.kBrake);

        spotlight = new Spark(0);
        spotlight.addChild(spotlight);

        distancePerPulse = DistancePerPulse.get(highestGearTeethNumber, lowestGearTeethNumber, wheelDiameter);

        leftEncoder = leftFront.getEncoder();
        leftEncoder.setPositionConversionFactor(distancePerPulse);
        leftEncoder.setPosition(0);

        rightEncoder = rightFront.getEncoder();
        rightEncoder.setPositionConversionFactor(distancePerPulse);
        rightEncoder.setPosition(0);

        drivePosition = () -> (rightEncoder.getPosition() - leftEncoder.getPosition()) / 2;
    }

    @Override
    public void initDefaultCommand() {

        setDefaultCommand(new DriveWithJoysticks());


        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Drive Position", this::getDrivePosition, null);
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void arcadeDrive(double xSpeed, double zRotation) {

        differentialDrive1.arcadeDrive(xSpeed, -zRotation, false);
    }

    //DO NOT DELETE. ONLY USED WITH TURN TO ANGLE. DONT DELETE
    public void tankDrive(double leftSpeed, double rightSpeed) {

        differentialDrive1.tankDrive(-leftSpeed, -rightSpeed, true);
    }

    /*
     * public void autonomousRotate(double leftSpeed, double rightSpeed) { // DONT
     * Use 'squaredInputs' or deadband in autonomous driveTrain.setDeadband(0);
     * driveTrain.tankDrive(leftSpeed, rightSpeed, false); }
     */
    public boolean getUseSplitArcade() {
        return this.useSplitArcade;
    }

    public void setUseSplitArcade(boolean useSplitArcade) {
        this.useSplitArcade = useSplitArcade;
    }

    public void setDeadband(double deadband) {
        differentialDrive1.setDeadband(deadband);
    }

    public void hDrive(double leftTrigger, double rightTrigger) {
        if (leftTrigger > -1 && rightTrigger > -1) {
            hDrive.set(0);
        } else if (leftTrigger > -1) {
            hDrive.set((leftTrigger + 1) / 2);
        } else if (rightTrigger > -1) {
            hDrive.set(-(rightTrigger + 1) / 2);
        } else {
            hDrive.set(0);
        }
    }

    public void setSpotlight(boolean on) {
        spotlight.set(on ? 1 : 0);
    }

    //returns inches
    public double getDrivePosition() {
        return drivePosition.getAsDouble();
    }

    public void resetDriveEncoders() {
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }
}