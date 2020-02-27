package frc.robot.subsystems.ball.shooter;

import java.util.concurrent.TimeUnit;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import ca.team3161.lib.robot.LifecycleEvent;
import ca.team3161.lib.robot.motion.drivetrains.SpeedControllerGroup;
import ca.team3161.lib.robot.pid.PIDRateValueSrc;
import ca.team3161.lib.robot.pid.PIDulum;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import ca.team3161.lib.utils.SmartPIDTuner;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class PIDFShooterImpl extends RepeatingPooledSubsystem implements Shooter {

    static final float SHOOTER_RPM_TARGET = 6100;
    static final float PIDF_DEADBAND = 100;
    static final float KP = 0.0001f;
    static final float KI = 0;
    static final float KD = 0;
    static final float KF = 0;

    WPI_TalonSRX shooterController1;
    WPI_TalonSRX shooterController2;
    SpeedControllerGroup controllers;
    // Using a PIDulum but with a hardcoded "angle" as a PIDF controller
    PIDulum pidf;
    TalonEncoderPIDSrc rateSrc;
    SmartPIDTuner pidTuner;

    DoubleSolenoid hatch;

    volatile boolean shooting;

    public PIDFShooterImpl(WPI_TalonSRX talon1, WPI_TalonSRX talon2, DoubleSolenoid sol) {
        super(20, TimeUnit.MILLISECONDS); // Probably will be a good value
        this.shooterController1 = talon1;
        this.shooterController2 = talon2;

        this.controllers = new SpeedControllerGroup(shooterController1, shooterController2);

        this.shooterController2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 1);
        this.rateSrc = new TalonEncoderPIDSrc(this.shooterController2);

        this.pidf = new PIDulum(rateSrc, PIDF_DEADBAND, 500, TimeUnit.MILLISECONDS,
                KP, KI, KD, 0, KF);

        this.pidTuner = new SmartPIDTuner("shooterPIDF", pidf, KP, KI, KD);

        this.hatch = sol;

        this.shooting = false;
    }

    public PIDFShooterImpl() {
        this(new WPI_TalonSRX(RobotMap.SHOOTER_TALON_PORTS[0]), new WPI_TalonSRX(RobotMap.SHOOTER_TALON_PORTS[1]), new DoubleSolenoid(RobotMap.SHOOTER_SOLENOID_CHANNELS[0], RobotMap.SHOOTER_SOLENOID_CHANNELS[1]));
    }

    private double getShooterRPM() {
        return rateSrc.getPIDValue();
    }

    private void setShooterSpeed(double speed) {
        controllers.set(speed);
    }

    public boolean getHatch() {
        return this.hatch.get() == DoubleSolenoid.Value.kForward;
    }

    public void setHatch(boolean position) {
        if (position) {
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
        require(controllers);
        require(hatch);
    }

    @Override
    public void task(){
        if (this.shooting) {
            setShooterSpeed(pidf.pid(SHOOTER_RPM_TARGET));
        } else {
            this.controllers.stopMotor();
        }

        SmartDashboard.putNumber("Shooter RPM", this.getShooterRPM());
    }

    public boolean readyForBalls() {
        // TODO just verify that the shooter has spun up, not necessarily that it's maintaining target speed
        return pidf.atTarget();
    }

    public void stopShooter() {
        this.shooting = false;
    }

    @Override
    public void lifecycleStatusChanged(LifecycleEvent previous, LifecycleEvent current) {
        this.pidTuner.lifecycleStatusChanged(previous, current);
        if (current.equals(LifecycleEvent.ON_AUTO) || current.equals(LifecycleEvent.ON_TELEOP)) {
            start();
        } else if(current.equals(LifecycleEvent.ON_DISABLED)) {
            stopShooter();
            controllers.stopMotor();
            cancel();
        }
    }

    static class TalonEncoderPIDSrc implements PIDRateValueSrc {

        private WPI_TalonSRX talon;

        TalonEncoderPIDSrc(WPI_TalonSRX talon) {
            this.talon = talon;
        }

        @Override
        public PIDSource getSensor() {
            return null;
        }

        @Override
        public Float getPIDValue() {
            return (float) (-(this.talon.getSelectedSensorVelocity() / 4096) * 600);
        }

        @Override
        public void setPIDSourceType(PIDSourceType pidSource) { }

        @Override
        public PIDSourceType getPIDSourceType() {
            return PIDSourceType.kRate;
        }

    }
}

