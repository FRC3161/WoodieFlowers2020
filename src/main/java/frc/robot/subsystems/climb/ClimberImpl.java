package frc.robot.subsystems.climb;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.RobotMap;


public class ClimberImpl implements Climber {
    
    WPI_TalonSRX lifterMotorController;

    // Pretty sure there are two solenoids
    DoubleSolenoid climberSolenoid;
    
    public ClimberImpl() {
        this.lifterMotorController = new WPI_TalonSRX(RobotMap.LIFTER_TALON_PORT);

        this.climberSolenoid = new DoubleSolenoid(RobotMap.CLIMBER_SOLENOID_CHANNELS[0], RobotMap.CLIMBER_SOLENOID_CHANNELS[1]);
    }

    public void extendClimber() {
        this.climberSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void retractClimber() {
        this.climberSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void liftRobot(){
        this.lifterMotorController.set(0.8d); // Determine actual value
        this.retractClimber();
    }

    public void stopClimber() {
        this.lifterMotorController.set(0.0d);
    }
}