package frc.robot.subsystems.ball.shooter;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import java.util.concurrent.TimeUnit;

import ca.team3161.lib.robot.LifecycleEvent;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import ca.team3161.lib.utils.SmartDashboardTuner;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class ShooterImpl extends RepeatingPooledSubsystem implements Shooter {

    WPI_TalonSRX shooterController1;
    WPI_TalonSRX shooterController2;

    DoubleSolenoid hatch;
    
    volatile boolean shooting;

    double shooterRPMTrench;
    SmartDashboardTuner rpmTuner;

    public ShooterImpl(WPI_TalonSRX talon1, WPI_TalonSRX talon2, DoubleSolenoid sol) {
        super(20, TimeUnit.MILLISECONDS); // Probably will be a good value
        this.shooterController1 = talon1;
        this.shooterController2 = talon2;

        this.shooterController2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 1);

        this.hatch = sol;

        this.shooting = false;

        this.shooterRPMTrench = 7500;
        this.rpmTuner = new SmartDashboardTuner("Shooter RPM Trench", shooterRPMTrench, d -> this.shooterRPMTrench = d);
        this.rpmTuner.start();
    }

    public ShooterImpl() {
        this(new WPI_TalonSRX(RobotMap.SHOOTER_TALON_PORTS[0]), new WPI_TalonSRX(RobotMap.SHOOTER_TALON_PORTS[1]), new DoubleSolenoid(RobotMap.SHOOTER_SOLENOID_CHANNELS[0], RobotMap.SHOOTER_SOLENOID_CHANNELS[1]));
    }

    private double getShooterRPM() {
        return ((this.shooterController2.getSelectedSensorVelocity() / 4096) * 600);
    }

    private void setShooterSpeed(double speed) {
        this.shooterController1.set(speed);
        this.shooterController2.set(speed);
    }

    public boolean getHatch() {
        if(this.hatch.get() == DoubleSolenoid.Value.kForward) {
            return true;
        } else {
            return false;
        }
    }

    public void setHatch(boolean position) {
        if(position){
            this.hatch.set(DoubleSolenoid.Value.kForward);
        } else {
            this.hatch.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public void runShooter() {
        this.shooting = true;
    }

    @Override
    public void defineResources() {
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

        SmartDashboard.putNumber("Shooter RPM", this.getShooterRPM());
    }

    public boolean readyForBalls() {
        if (this.getShooterRPM() > this.shooterRPMTrench) {
            return true;
        }
        return false;
    }

    public void stopShooter() {
        this.shooting = false;
    }

    @Override
    public void lifecycleStatusChanged(LifecycleEvent previous, LifecycleEvent current) {
        if(current.equals(LifecycleEvent.ON_AUTO) || current.equals(LifecycleEvent.ON_TELEOP)) {
            start();
        } else if(current.equals(LifecycleEvent.ON_DISABLED)) {
            cancel();
        }
    }
}