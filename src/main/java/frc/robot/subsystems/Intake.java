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


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
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
public enum RollerDirection {INTAKE, EJECT}

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private CANSparkMax IntakeFlywheel;
private CANSparkMax intakeLeft;
private CANSparkMax intakeRight;
private CANSparkMax intakeMain;
private Spark flywheel;
private Solenoid flywheelSolenoid;
private Encoder colourWheelEncoder;
private Spark angleAdjustment;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Intake() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

IntakeFlywheel = new CANSparkMax(5, MotorType.kBrushless); //Flywheel

IntakeFlywheel.restoreFactoryDefaults();  
IntakeFlywheel.setInverted(false);
IntakeFlywheel.setIdleMode(IdleMode.kCoast); //Agreed to keep it in coast mode
  
        
intakeLeft = new CANSparkMax(6, MotorType.kBrushless); //Align Arm Left

intakeLeft.restoreFactoryDefaults();  
intakeLeft.setInverted(false);
intakeLeft.setIdleMode(IdleMode.kBrake);
  
        
intakeRight = new CANSparkMax(7, MotorType.kBrushless); //Align Arm Right

intakeRight.restoreFactoryDefaults();  
intakeRight.setInverted(false);
intakeRight.setIdleMode(IdleMode.kBrake);
  
        
intakeMain = new CANSparkMax(8, MotorType.kBrushless); //Tube Intake

intakeMain.restoreFactoryDefaults();  
intakeMain.setInverted(false);
intakeMain.setIdleMode(IdleMode.kBrake);
  
        
flywheel = new Spark(8);
addChild("Flywheel",flywheel);
flywheel.setInverted(false);
        
flywheelSolenoid = new Solenoid(0, 0);
addChild("Flywheel Solenoid",flywheelSolenoid);

        
colourWheelEncoder = new Encoder(0, 1, false, EncodingType.k4X);
addChild("Colour Wheel Encoder",colourWheelEncoder);
colourWheelEncoder.setDistancePerPulse(1.0);
colourWheelEncoder.setPIDSourceType(PIDSourceType.kRate);
        
angleAdjustment = new Spark(9);
addChild("Angle Adjustment",angleAdjustment);
angleAdjustment.setInverted(false);
}
        
    
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    public void intakeFlywheel(double speed, RollerDirection state){
        if (state == RollerDirection.INTAKE){
            IntakeFlywheel.set(-speed);
        }
        else {
            //EJECT
            IntakeFlywheel.set(speed);
        }
    }

    public boolean getIntakeFlywheel(){
        return (IntakeFlywheel.get() > 0.05);
        //return if sufficient speed?
    }

    public void intakeRightLeft(double speed, RollerDirection state){
        if (state == RollerDirection.EJECT){
            intakeLeft.set(0);
            intakeRight.set(0);
        }
        else {
            intakeLeft.set(speed);
            intakeRight.set(-speed);
            //don't need opposite speed
        }
    }

    public void intakeMain(double speed, RollerDirection state){
        if(state == RollerDirection.INTAKE){
            intakeMain.set(-speed);
        }
        else {
            //EJECT
            intakeMain.set(speed);
        }
    }
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}
