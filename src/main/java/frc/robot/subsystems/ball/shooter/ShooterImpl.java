package frc.robot.subsystems.ball.shooter;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import java.util.concurrent.TimeUnit;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import ca.team3161.lib.utils.SmartDashboardTuner;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.RobotMap;

public class ShooterImpl  extends RepeatingPooledSubsystem implements Shooter{
    
    WPI_TalonSRX shooterController1;
    WPI_TalonSRX shooterController2;

    DoubleSolenoid hatch;
    
    boolean shooting;

    double shooterRPMTrench;
    SmartDashboardTuner rpmTuner;
   
    public ShooterImpl(){
        super(20, TimeUnit.MILLISECONDS); // Probably will be a good value 
        this.shooterController1 = new WPI_TalonSRX(RobotMap.SHOOTER_TALON_PORTS[0]);
        this.shooterController2 = new WPI_TalonSRX(RobotMap.SHOOTER_TALON_PORTS[1]);

        this.shooterController1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 1);
        
        this.hatch = new DoubleSolenoid(RobotMap.SHOOTER_SOLENOID_CHANNELS[0], RobotMap.SHOOTER_SOLENOID_CHANNELS[1]);

        this.shooting = false;

        this.shooterRPMTrench = 7500;
        this.rpmTuner = new SmartDashboardTuner("Shooter RPM Trench",  shooterRPMTrench, d -> this.shooterRPMTrench = d);
        this.rpmTuner.start();
    }

    private double getShooterRPM() {
        return ((this.shooterController1.getSelectedSensorVelocity() / 4096) * 600);
    }

    private void setShooterSpeed(double speed) {
        this.shooterController1.set(speed);
        this.shooterController2.set(speed);
    }

    public Value getHatch() {
        return this.hatch.get();
    }

    public void setHatch(Value position) {
        this.hatch.set(position);
    }
    
    public void runShooter() {
        this.shooting = true;
    }

    @Override
    public void defineResources(){
        require(shooterController1);
        require(shooterController2);
        require(hatch);
    }

    @Override
    public void task(){
        if(this.shooting) {
            if (getShooterRPM() < shooterRPMTrench){
                this.setShooterSpeed(1.0d);
            } else {
                this.setShooterSpeed(0.0d);
            }
        } else {
            this.setShooterSpeed(0.0d);
        }
    }

    public boolean readyForBalls(){
        if (this.getShooterRPM() > this.shooterRPMTrench){
            return true;
        }    
        return false;
    }

    public void stopShooter() {
        this.shooting = false;
    }
}