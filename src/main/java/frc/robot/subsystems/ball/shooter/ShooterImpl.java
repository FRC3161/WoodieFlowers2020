package frc.robot.subsystems.ball.shooter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import java.util.concurrent.TimeUnit;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import ca.team3161.lib.utils.SmartDashboardTuner;
import edu.wpi.first.wpilibj.SpeedController;

import frc.robot.RobotMap;

public class ShooterImpl  extends RepeatingPooledSubsystem implements Shooter{
    
    SpeedController shooterController;
    Encoder shooterEncoder;

    double shooterRPM;
    SmartDashboardTuner rpmTuner;
   
    public ShooterImpl(SpeedController cntrl, Encoder e){
        super(50, TimeUnit.MILLISECONDS); // TODO figure out actual value
        this.shooterController = cntrl;
        
        this.shooterEncoder = e;

        this.shooterRPM = 4000;
        this.rpmTuner = new SmartDashboardTuner("Shooter RPM",  shooterRPM, d -> this.shooterRPM = d);
    }

    public ShooterImpl() {
        this(new WPI_TalonSRX(RobotMap.SHOOTER_TALON_PORT), new Encoder(RobotMap.SHOOTER_ENCODER_PORTS[0], RobotMap.SHOOTER_ENCODER_PORTS[1]));
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
        if (this.getShooterRPM() > this.shooterRPM){
            return true;
        }    
        return false;
    }

    public void stopShooter() {
        this.shooterController.set(0.0d);
    }
}