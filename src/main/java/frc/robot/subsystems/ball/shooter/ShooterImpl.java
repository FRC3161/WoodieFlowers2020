package frc.robot.subsystems.ball.shooter;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import java.util.concurrent.TimeUnit;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import ca.team3161.lib.utils.SmartDashboardTuner;

import frc.robot.RobotMap;

public class ShooterImpl  extends RepeatingPooledSubsystem implements Shooter{
    
    WPI_TalonSRX shooterController1;
    WPI_TalonSRX shooterController2;

    double shooterRPMTrench;
    SmartDashboardTuner rpmTuner;
   
    public ShooterImpl(){
        super(20, TimeUnit.MILLISECONDS); // Probably will be a good value 
        this.shooterController1 = new WPI_TalonSRX(RobotMap.SHOOTER_TALON_PORTS[0]);
        this.shooterController2 = new WPI_TalonSRX(RobotMap.SHOOTER_TALON_PORTS[1]);

        this.shooterController1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 1);

        this.shooterRPMTrench = 7500;
        this.rpmTuner = new SmartDashboardTuner("Shooter RPM",  shooterRPMTrench, d -> this.shooterRPMTrench = d);
    }

    private double getShooterRPM() {
        return ((this.shooterController1.getSelectedSensorVelocity() / 4096) * 600);
    }
    
    public void runShooter() {
        if (getShooterRPM() < shooterRPMTrench){
            this.shooterController1.set(1.0d);
            return;
        }
        this.shooterController1.set(0.0d);
    }

    @Override
    public void defineResources(){
        require(shooterController1);
    }

    @Override
    public void task(){
        return;
        //Placeholder
    }

    public boolean readyForBalls(){
        if (this.getShooterRPM() > this.shooterRPMTrench){
            return true;
        }    
        return false;
    }

    public void stopShooter() {
        this.shooterController1.set(0.0d);
    }
}