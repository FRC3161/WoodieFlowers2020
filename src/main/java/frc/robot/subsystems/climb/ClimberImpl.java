package frc.robot.subsystems.climb;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.RobotMap;

public class ClimberImpl implements Climber {
    
    WPI_TalonSRX climberMotorController;
    
    public ClimberImpl() {
        this.climberMotorController = new WPI_TalonSRX(RobotMap.CLIMBER_TALON_PORT);
    }

    public void move(char direction) {
        // TODO determine which direction is positive and which is negative
        if(direction == 'L') {
            this.climberMotorController.set(0.60);
        } else if(direction == 'R') {
            this.climberMotorController.set(-0.60);
        }
    }
}