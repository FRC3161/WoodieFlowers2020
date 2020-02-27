package frc.robot.subsystems.climb;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.RobotMap;


public class ClimberImpl implements Climber {
    
    WPI_TalonSRX lifterMotorController;

    // Pretty sure there are two solenoids
    DoubleSolenoid climberSolenoid1;
    DoubleSolenoid climberSolenoid2;
    
    public ClimberImpl() {
        this.lifterMotorController = new WPI_TalonSRX(RobotMap.LIFTER_TALON_PORT);

        this.climberSolenoid1 = new DoubleSolenoid(RobotMap.CLIMBER_SOLENOID1_CHANNELS[0], RobotMap.CLIMBER_SOLENOID1_CHANNELS[1]);
        this.climberSolenoid2 = new DoubleSolenoid(RobotMap.CLIMBER_SOLENOID2_CHANNELS[0], RobotMap.CLIMBER_SOLENOID2_CHANNELS[1]);
    }

    public void extendClimber() {
        this.climberSolenoid1.set(DoubleSolenoid.Value.kForward);
        this.climberSolenoid2.set(DoubleSolenoid.Value.kForward);
    }

    public void retractClimber() {
        this.climberSolenoid1.set(DoubleSolenoid.Value.kReverse);
        this.climberSolenoid2.set(DoubleSolenoid.Value.kReverse);
    }

    public void liftRobot(){
        this.lifterMotorController.set(0.8d); // Determine actual value
    }

    public void stopClimber() {
        this.lifterMotorController.set(0.0d);
    }
}