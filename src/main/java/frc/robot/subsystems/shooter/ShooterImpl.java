package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ShooterImpl  implements Shooter{
    
    WPI_TalonSRX shooterController;
    
    public ShooterImpl() {
        this.shooterController  = new WPI_TalonSRX(frc.robot.RobotMap.SHOOTER_TALON_PORT);
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
}