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

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.utils.frc4048.Logging;
/**
 *
 */
public class Pneumatics extends Subsystem {

    private final Compressor compressor;
    private final AnalogInput compressorPressure;
    

    public Pneumatics() {

        compressor = new Compressor(11);
        addChild("Compressor",compressor);

        compressorPressure = new AnalogInput(0);
        addChild("Compressor Pressure", compressorPressure);

    }

    public void reset(){
        //compressor.start();
    }
    public boolean hasEnoughPressure() {
        //return Robot.pneumatics.getPressure() > 40;
        return true;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addBooleanProperty("ClosedLoop", compressor::getClosedLoopControl, compressor::setClosedLoopControl);
        builder.addDoubleProperty("Pressure", this::getPressure, null);
    }

    @Override
    public void initDefaultCommand() {
    }

    public double getPressure(){
        //http://www.revrobotics.com/content/docs/REV-11-1107-DS.pdf formula for pressure
        double vout = compressorPressure.getAverageVoltage();
        double vcc = RobotController.getVoltage5V();

        double pressure = 250*(vout/vcc)-25;

        return pressure;
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public Logging.LoggingContext loggingContext = new Logging.LoggingContext(Logging.Subsystems.PNEUMATICS){

        @Override
        protected void addAll(){
            add("Pressure", getPressure());
        }
    };

}
