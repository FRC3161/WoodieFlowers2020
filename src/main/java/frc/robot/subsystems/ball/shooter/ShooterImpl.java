package frc.robot.subsystems.ball.shooter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import java.util.concurrent.TimeUnit;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import ca.team3161.lib.utils.SmartDashboardTuner;
import frc.robot.RobotMap;

public class ShooterImpl  extends RepeatingPooledSubsystem implements Shooter{
    
    WPI_TalonSRX shooterController;
    Encoder shooterEncoder;

    double shooterRPM;
    SmartDashboardTuner rpmTuner;
    
    public ShooterImpl() {
       
        super(50, TimeUnit.MILLISECONDS); // TODO figure out actual value
        this.shooterController  = new WPI_TalonSRX(frc.robot.RobotMap.SHOOTER_TALON_PORT);
        
        this.shooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER_PORTS[0], RobotMap.SHOOTER_ENCODER_PORTS[1]);

        this.shooterRPM = 4000;
        this.rpmTuner = new SmartDashboardTuner("Shooter RPM",  shooterRPM, d -> this.shooterRPM = d);
    }

    private double getShooterRPM() {
        return ((this.shooterEncoder.getRate() / 128) * 60);
    }
    
    public void runShooter() {
        if (getShooterRPM() < shooterRPM){
            this.shooterController.set(1.0d);
            return;
        }
        this.shooterController.set(0.0d);
    }

    @Override
    public void defineResources(){
        require(shooterController);
        require(shooterEncoder);
    }

    @Override
    public void task(){
        return;
        //Placeholder
    }

    public boolean readyForBalls(){
        if (this.getShooterRPM() > shooterRPM){
            return true;
        }    
        return false;
    }

    public void stopShooter() {
        this.shooterController.set(0.0d);
    }
}