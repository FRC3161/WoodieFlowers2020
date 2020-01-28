package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;

public class ShooterImpl  implements Shooter{
    
    WPI_TalonSRX shooterController;
    Solenoid shooterSolenoid;
    
    public ShooterImpl() {
        this.shooterController  = new WPI_TalonSRX(frc.robot.RobotMap.SHOOTER_TALON_PORT);
        this.shooterSolenoid = new Solenoid(RobotMap.SHOOTER_SOLENOID_CHANNEL);
    }
    
    public void shoot(boolean shooting) {
        if (shooting){
            this.shooterController.set(1.0d);
            return;
        }
            this.shooterController.set(0.0d);
    }

    public boolean getPosition(){
        // Returns true if it is up, otherwise false
        if(this.shooterSolenoid.get()) {
            return true;
        }
        return false;
    }

    public void invertPosition(){
        if(getPosition()) {
            this.shooterSolenoid.set(true);
            return;
        }
        this.shooterSolenoid.set(false);
    }
}