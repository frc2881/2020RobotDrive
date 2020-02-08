// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AngleCalibrateEncoder;
import frc.robot.commands.ArmControl;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.CameraSwitch;
import frc.robot.commands.DoNothing;
import frc.robot.commands.DriveForDistance;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.IntakeControlRollers;
import frc.robot.commands.IntakeSetAsBack;
import frc.robot.commands.IntakeSetAsFront;
import frc.robot.commands.LiftControl;
import frc.robot.commands.LiftToHeight;
import frc.robot.commands.PowerCellControl;
import frc.robot.commands.PowerCellControlRollers;
import frc.robot.commands.PowerCellIntake;
import frc.robot.commands.PowerCellSetRoller;
import frc.robot.commands.Rendezvous;
import frc.robot.commands.RobotPrep;
import frc.robot.commands.RumbleDriver;
import frc.robot.commands.RumbleJoysticks;
import frc.robot.commands.RumbleNo;
import frc.robot.commands.RumbleYes;
import frc.robot.commands.SetArmAngle;
import frc.robot.commands.TWINKLES;
import frc.robot.commands.TrenchPrep;
import frc.robot.commands.TurnToAngle;
import frc.robot.commands.WaitForPressure;
import frc.robot.commands.WaitForever;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton driverGreenTriangle;
    public JoystickButton driverBlueX;
    public JoystickButton driverPinkSquare;
    public JoystickButton driverRedCircle;
    public JoystickButton driverPOV;
    public JoystickButton driverOption;
    public JoystickButton driverShare;
    public JoystickButton manipulatorGreenTriangle;
    public JoystickButton manipulatorBlueX;
    public JoystickButton manipulatorPinkSquare;
    public JoystickButton manipulatorRedCircle;
    public JoystickButton manipulatorPOV;
    public JoystickButton manipulatorOption;
    public JoystickButton manipulatorShare;
    public Joystick driver;
    public Joystick manipulator;
    public NetworkTableEntry straightPID;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {

        straightPID = Shuffleboard.getTab("PID").add("Straight PID", 0)
                .withWidget(BuiltInWidgets.kNumberBar).getEntry();

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        manipulator = new Joystick(1);

        driver = new Joystick(0);

        driverShare = new JoystickButton(driver, 9);
        driverShare.whileHeld(new DoNothing());

        driverOption = new JoystickButton(driver, 10);
        driverOption.whileHeld(new DoNothing());

        driverPOV = new JoystickButton(driver, 1);
        driverPOV.whileHeld(new DoNothing());

        driverPinkSquare = new JoystickButton(driver, 1);
        driverPinkSquare.whileHeld(new TurnToAngle(270));

        driverBlueX = new JoystickButton(driver, 2);
        driverBlueX.whenPressed(new IntakeSetAsBack());

        driverRedCircle = new JoystickButton(driver, 3);
        driverRedCircle.whileHeld(new TurnToAngle(90));

        driverGreenTriangle = new JoystickButton(driver, 4);
        driverGreenTriangle.whenPressed(new IntakeSetAsFront());

        // SmartDashboard Buttons
        SmartDashboard.putData("Arm Control", new ArmControl());
        SmartDashboard.putData("Angle Calibrate Encoder", new AngleCalibrateEncoder());
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("Do Nothing", new DoNothing());
        SmartDashboard.putData("Drive With Joysticks", new DriveWithJoysticks());
        SmartDashboard.putData("Intake Control Rollers", new IntakeControlRollers());
        SmartDashboard.putData("Lift Control", new LiftControl());
        SmartDashboard.putData("Lift To Height", new LiftToHeight());
        SmartDashboard.putData("Set Arm Angle", new SetArmAngle());
        SmartDashboard.putData("Rendezvous", new Rendezvous());
        SmartDashboard.putData("Trench Prep", new TrenchPrep());
        SmartDashboard.putData("Robot Prep", new RobotPrep());
        SmartDashboard.putData("TWINKLES", new TWINKLES());
        SmartDashboard.putData("Rumble Driver", new RumbleDriver());
        SmartDashboard.putData("Rumble Joysticks", new RumbleJoysticks());
        SmartDashboard.putData("Rumble Yes", new RumbleYes());
        SmartDashboard.putData("Rumble No", new RumbleNo());
        SmartDashboard.putData("Intake Set As Front", new IntakeSetAsFront());
        SmartDashboard.putData("Intake Set As Back", new IntakeSetAsBack());
        SmartDashboard.putData("Camera Switch", new CameraSwitch());
        SmartDashboard.putData("Wait Forever", new WaitForever());
        SmartDashboard.putData("Wait For Pressure", new WaitForPressure());
        SmartDashboard.putData("Power Cell Control", new PowerCellControl());
        SmartDashboard.putData("Power Cell Set Roller", new PowerCellSetRoller());
        SmartDashboard.putData("Power Cell Control Rollers", new PowerCellControlRollers());
        SmartDashboard.putData("Power Cell Intake", new PowerCellIntake());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        SmartDashboard.putData("Drive Forward", new DriveForDistance(4));

        manipulatorShare = new JoystickButton(manipulator, 9);
        manipulatorShare.whileHeld(new DoNothing());

        manipulatorOption = new JoystickButton(manipulator, 10);
        manipulatorOption.whileHeld(new DoNothing());

        manipulatorPOV = new JoystickButton(manipulator, 14);
        manipulatorPOV.whileHeld(new DoNothing());

        manipulatorPinkSquare = new JoystickButton(manipulator, 1);
        manipulatorPinkSquare.whileHeld(new DoNothing());

        manipulatorBlueX = new JoystickButton(manipulator, 2);
        manipulatorBlueX.whenPressed(new DoNothing());

        manipulatorRedCircle = new JoystickButton(manipulator, 3);
        manipulatorRedCircle.whileHeld(new DoNothing());

        manipulatorGreenTriangle = new JoystickButton(manipulator, 4);
        manipulatorGreenTriangle.whenPressed(new DoNothing());//TODO figure out what POVs are
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriver() {
        return driver;
    }

    public Joystick getManipulator() {
        return manipulator;
    }

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

    // DRIVER Joysticks

    public double getDriverLeftX() {
        return driver.getRawAxis(0);
    }

    public double getDriverLeftY() {
        return driver.getRawAxis(1);
    }

    public double getDriverRightX() {
        return driver.getRawAxis(2);
    }

    public double getDriverRightY() {
        return driver.getRawAxis(5);
    }

    // DRIVER Triggers

    public double getDriverTriggerLeft() {
        return driver.getRawAxis(3);
    }

    public double getDriverTriggerRight() {
        return driver.getRawAxis(4);
    }

    // MANIPULATOR Joysticks

    public double getManipulatorLeftX() {
        return manipulator.getX(GenericHID.Hand.kLeft);
    }

    public double getManipulatorLeftY() {
        return manipulator.getY(GenericHID.Hand.kLeft);
    }

    public double getManipulatorRightX() {
        return manipulator.getX(GenericHID.Hand.kRight);
    }

    public double getManipulatorRightY() {
        return manipulator.getY(GenericHID.Hand.kRight);
    }

    // MANIPULATOR Triggers

    public double getManipulatorTriggerLeft() {
        return manipulator.getRawAxis(3);
    }

    public double getManipulatorTriggerRight() {
        return manipulator.getRawAxis(4);
    }

    public double getStraightPIDValue() {
        return straightPID.getDouble(0);
    }

}
