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
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.utils.DistancePerPulse;
import frc.robot.utils.frc4048.Logging;
// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class Drive extends Subsystem {

    public enum IntakeLocation {
        FRONT, BACK
    }
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    private static final double highestGearTeethNumber = 50;
    private static final double lowestGearTeethNumber = 12;
    private static final double encoderCountsPerRevolution = 42;
    private static final double wheelDiameter = 6;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private CANSparkMax leftFront;
    private CANSparkMax leftRear;
    private CANSparkMax rightFront;
    private CANSparkMax rightRear;
    private DifferentialDrive differentialDrive1;

    private IntakeLocation intakeLocation = IntakeLocation.FRONT;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private CANEncoder leftEncoder;
    private CANEncoder rightEncoder;
    private DoubleSupplier leftEncoderPosition;
    private DoubleSupplier leftEncoderVelocity;
    private DoubleSupplier rightEncoderPosition;
    private DoubleSupplier rightEncoderVelocity;
    private double x, y, d, a;
    private CANSparkMax hDrive;
    private double beginningPosition;

    public Drive() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        leftFront = new CANSparkMax(1, MotorType.kBrushless);

        leftFront.restoreFactoryDefaults();
        leftFront.setInverted(false);
        leftFront.setIdleMode(IdleMode.kBrake);

        leftRear = new CANSparkMax(2, MotorType.kBrushless);

        leftRear.restoreFactoryDefaults();
        leftRear.setInverted(false);
        leftRear.setIdleMode(IdleMode.kBrake);

        // Set SlaveSpeedControllers to Follow MasterSpeedController
        leftRear.follow(leftFront);

        rightFront = new CANSparkMax(3, MotorType.kBrushless);

        rightFront.restoreFactoryDefaults();
        rightFront.setInverted(false);
        rightFront.setIdleMode(IdleMode.kBrake);

        rightRear = new CANSparkMax(4, MotorType.kBrushless);

        rightRear.restoreFactoryDefaults();
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
        hDrive.restoreFactoryDefaults();
        hDrive.setInverted(false);
        hDrive.setIdleMode(IdleMode.kBrake);

        double distancePerPulse = DistancePerPulse.get(highestGearTeethNumber, lowestGearTeethNumber,
                encoderCountsPerRevolution, wheelDiameter);
        leftEncoder = leftRear.getEncoder();
        leftEncoderPosition = () -> leftEncoder.getPosition() * distancePerPulse;
        leftEncoderVelocity = () -> leftEncoder.getVelocity() * distancePerPulse;

        rightEncoder = rightRear.getEncoder();
        rightEncoderPosition = () -> rightEncoder.getPosition() * distancePerPulse;
        rightEncoderVelocity = () -> rightEncoder.getVelocity() * distancePerPulse;

        beginningPosition = ((leftEncoder.getPosition() + rightEncoder.getPosition()) / 2) * distancePerPulse;

    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DriveWithJoysticks());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addStringProperty("Location", this::getLocation, null);
        builder.addDoubleProperty("X", () -> x, null);
        builder.addDoubleProperty("Y", () -> y, null);
    }

    public String getLocation() {
        return String.format("(%.2f,%.2f) %.1f°", x, y, a);
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void tankDrive(double leftSpeed, double rightSpeed) {

        // if(intakeLocation == IntakeLocation.FRONT) {
        // differentialDrive.tankDrive(leftSpeed, rightSpeed, true);
        // }
        // else {
        // differentialDrive.tankDrive(-rightSpeed, -leftSpeed, true);
        // }
        if (intakeLocation == IntakeLocation.FRONT) {
            differentialDrive1.tankDrive(leftSpeed, rightSpeed, true);
        } else {
            differentialDrive1.tankDrive(-rightSpeed, -leftSpeed, true);
        }
    }

    public void setIntakeLocation(IntakeLocation intakeLocation) {
        this.intakeLocation = intakeLocation;
    }

    public void autonomousArcadeDrive(double straightSpeed, double rotateSpeed) {
        // DONT Use 'squaredInputs' or deadband in autonomous
        differentialDrive1.setDeadband(0);
        differentialDrive1.arcadeDrive(straightSpeed, rotateSpeed, false);
    }

    public double getDriveEncoderDistance() {
        return (leftEncoderPosition.getAsDouble() + rightEncoderPosition.getAsDouble()) / 2;
    }

    public final Logging.LoggingContext loggingContext = new Logging.LoggingContext(Logging.Subsystems.DRIVE) {

        @Override
        protected void addAll() {
            add("Drive Position", getDriveEncoderDistance());
        }

    };

    public void hDrive(double leftTrigger, double rightTrigger){
        if(leftTrigger>-1&&rightTrigger>-1){
            hDrive.set(0);
        }
        else if(leftTrigger>-1){
            if(intakeLocation == IntakeLocation.FRONT){
                hDrive.set((leftTrigger+1)/2);
            }
            else{
                hDrive.set(-(leftTrigger+1)/2);
            }
        }
        else if(rightTrigger>-1){
            if(intakeLocation == IntakeLocation.FRONT){
                hDrive.set(-(rightTrigger+1)/2);
            }
            else{
                hDrive.set((rightTrigger+1)/2);
            }
        }
        else{
            hDrive.set(0);
        }
    }
    /*
    set speed to +1, /2
    -1 to 1
    */

    public void setDriveSpeed(double speed) {
        if (speed > 1) {
            speed = 1;
        }

        if (speed < -1) {
            speed = -1;
        }

        if ((speed > 0) && (speed <= 0.1)) {
            speed = 0.1;
        }

        if ((speed < 0) && (speed > -0.1)) {
            speed = -0.1;
        }

        differentialDrive1.tankDrive(speed, speed);
    }

}