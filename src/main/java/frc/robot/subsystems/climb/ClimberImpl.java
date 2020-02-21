package frc.robot.subsystems.climb;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;


public class ClimberImpl implements Climber {
    
    WPI_TalonSRX lifterMotorController;

    // Pretty sure there are two solenoids
    Solenoid climberSolenoid1;
    Solenoid climberSolenoid2;
    
    public ClimberImpl() {
        this.lifterMotorController = new WPI_TalonSRX(RobotMap.LIFTER_TALON_PORT);

        this.climberSolenoid1 = new Solenoid(RobotMap.CLIMBER_SOLENOID1_CHANNEL);
        this.climberSolenoid2 = new Solenoid(RobotMap.CLIMBER_SOLENOID2_CHANNEL);
    }

    // Consider making one method that inverts the climber instead of extending it or retracting it

    public void extendClimber() {
        // Not sure if there are any negative consequences to trying to enable an already enabled solenoid, but it can't hurt
        if(!this.climberSolenoid1.get() && !this.climberSolenoid2.get()){
            this.climberSolenoid1.set(true);
            this.climberSolenoid2.set(true);
        }
    }

    public void retractClimber() {
        if(this.climberSolenoid1.get() && this.climberSolenoid2.get()) {
            this.climberSolenoid1.set(false);
            this.climberSolenoid2.set(false);
        }
    }

    public void liftRobot(){
        this.lifterMotorController.set(0.8d); // Determine actual value
    }
}