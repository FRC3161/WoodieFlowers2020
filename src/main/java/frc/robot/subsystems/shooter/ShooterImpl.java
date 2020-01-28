package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import java.util.concurrent.TimeUnit;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import frc.robot.RobotMap;

public class ShooterImpl  extends RepeatingPooledSubsystem implements Shooter{
    
    WPI_TalonSRX shooterController;
    WPI_TalonSRX beltController;
    WPI_TalonSRX rollerController;

    Solenoid shooterSolenoid;
    Encoder shooterEncoder;
    
    public ShooterImpl() {
       
        super(50, TimeUnit.MILLISECONDS); // TODO figure out actual value
        this.rollerController = new WPI_TalonSRX(RobotMap.ROLLER_TALON_PORT);
        this.beltController = new WPI_TalonSRX(RobotMap.BELT_TALON_PORT);
        this.shooterController  = new WPI_TalonSRX(frc.robot.RobotMap.SHOOTER_TALON_PORT);
        
        this.shooterSolenoid = new Solenoid(RobotMap.SHOOTER_SOLENOID_CHANNEL);
        this.shooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER_PORTS[0], RobotMap.SHOOTER_ENCODER_PORTS[1]);
    }

    private double getShooterRPM() {
        return ((this.shooterEncoder.getRate() / 128) * 60);
    }
    
    public void shoot(boolean shooting) {
        if (shooting){
            if (getShooterRPM() < 4000){
                this.shooterController.set(1.0d);
            }
            return;
        }
            this.shooterController.set(0.0d);
    }

    @Override
    public void defineResources(){
        require(shooterController);
        require(beltController);
        require(rollerController);
    }

    @Override
    public void task(){
        return;
        //Placeholder
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