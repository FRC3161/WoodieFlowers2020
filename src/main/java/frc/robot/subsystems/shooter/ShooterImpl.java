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
    
    public void shoot() {
        // Right now this is a placeholder to allow us to test
        this.shooterController.set(1.0d);
        try{
            Thread.sleep(5000);
        } catch(InterruptedException e){
            System.out.println("Shooting");
        }
        this.shooterController.set(0.0d);
    }

    public boolean getPosition(){
        // Returns true if it is up, otherwise false
    }
}