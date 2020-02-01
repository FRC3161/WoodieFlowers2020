package frc.robot.subsystems.climb;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;

public class ClimberImpl implements Climber {
    
    WPI_TalonSRX climberMotorController;
    WPI_TalonSRX lifterMotorController;

    // Pretty sure there are two solenoids
    Solenoid climberSolenoid1;
    Solenoid climberSolenoid2;
    
    public ClimberImpl() {
        this.climberMotorController = new WPI_TalonSRX(RobotMap.CLIMBER_TALON_PORT);
        this.lifterMotorController = new WPI_TalonSRX(RobotMap.LIFTER_TALON_PORT);

        this.climberSolenoid1 = new Solenoid(RobotMap.CLIMBER_SOLENOID1_CHANNEL);
        this.climberSolenoid2 = new Solenoid(RobotMap.CLIMBER_SOLENOID2_CHANNEL);
    }

    public void move(char direction) {
        // TODO determine which direction is positive and which is negative
        if(direction == 'L') {
            this.climberMotorController.set(0.60);
        } else if(direction == 'R') {
            this.climberMotorController.set(-0.60);
        }
    }

    public void extendClimber() {
        // PLACEHOLDER
        return;
    }

    public void retractClimber() {
        // PLACEHOLDER
        return;
    }

    public void liftRobot(){
        // PLACEHOLDER
        return;
    }
}