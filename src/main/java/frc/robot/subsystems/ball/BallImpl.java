package frc.robot.subsystems.ball;

import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import java.util.concurrent.TimeUnit;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.RobotMap;
import frc.robot.subsystems.ball.feeder.Feeder;
import frc.robot.subsystems.ball.shooter.PIDFShooterImpl;
import frc.robot.subsystems.ball.shooter.Shooter;
import frc.robot.subsystems.ball.shooter.ShooterImpl;
import frc.robot.subsystems.ball.intake.Intake;

import ca.team3161.lib.robot.LifecycleEvent;

import frc.robot.subsystems.ball.feeder.FeederImpl;
import frc.robot.subsystems.ball.intake.IntakeImpl;

public class BallImpl extends RepeatingPooledSubsystem implements Ball {

    static final String PIDF_CONTROLLER_STRATEGY = "pidf";
    static final String BANGBANG_CONTROLLER_STRATEGY = "bangbang";

    private Shooter shooter;
    private Feeder feeder;
    private Intake intake;

    WPI_TalonSRX shooterController1;
    WPI_TalonSRX shooterController2;
    DoubleSolenoid shooterHatch;

    SendableChooser controllerChooser;

    private boolean shootEnabled;

    public BallImpl() {
        // PLACEHOLDER
        super(100, TimeUnit.MILLISECONDS);

        this.shooterController1 = new WPI_TalonSRX(RobotMap.SHOOTER_TALON_PORTS[0]);
        this.shooterController2 = new WPI_TalonSRX(RobotMap.SHOOTER_TALON_PORTS[1]);
        this.shooterHatch = new DoubleSolenoid(RobotMap.SHOOTER_SOLENOID_CHANNELS[0], RobotMap.SHOOTER_SOLENOID_CHANNELS[1]);

        this.controllerChooser = new SendableChooser();
        this.controllerChooser.addDefault(BANGBANG_CONTROLLER_STRATEGY, BANGBANG_CONTROLLER_STRATEGY);
        this.controllerChooser.addObject(PIDF_CONTROLLER_STRATEGY, PIDF_CONTROLLER_STRATEGY);

        this.shooter = new ShooterImpl(shooterController1, shooterController2, shooterHatch);
        this.feeder = new FeederImpl();
        this.intake = new IntakeImpl();

        this.intake.start();
        this.shooter.start();
        this.shootEnabled = false;
    }

    public void shoot() {
        this.shootEnabled = true;
    }

    public void cancelShooting() {
        this.shootEnabled = false;
    }

    public void stop() {
        this.shooter.stopShooter();
        this.feeder.stopAll();
        this.shootEnabled = false;
    }

    public void unload() {
        try {
            this.feeder.unload();
        } catch (InterruptedException e) {
        }
        // Seems kind of redundant, but abstraction
    }

    public void defineResources(){
        return;
    }

    public void collect() {
        this.intake.extend();
    }

    public void retract() {
        this.intake.retract();
    }

    public void task() {
        if(this.shootEnabled) {
            this.shooter.runShooter();
            this.feeder.enableHopper();
            if(this.shooter.readyForBalls()) {
                this.feeder.enableConveyor();
            } else {
                this.feeder.stopConveyor();
            }
        } else {
            this.shooter.stopShooter();
            this.feeder.stopAll();
        }
    }

    @Override
    public void lifecycleStatusChanged(LifecycleEvent previous, LifecycleEvent current) {
        this.shooter.lifecycleStatusChanged(previous, current);
        this.intake.lifecycleStatusChanged(previous, current);
        if(current.equals(LifecycleEvent.ON_AUTO) || current.equals(LifecycleEvent.ON_TELEOP)) {
            String shooterStrat = (String) controllerChooser.getSelected();
            switch (shooterStrat) {
                case PIDF_CONTROLLER_STRATEGY:
                    this.shooter = new PIDFShooterImpl(shooterController1, shooterController2, shooterHatch);
                    break;
                case BANGBANG_CONTROLLER_STRATEGY:
                    this.shooter = new ShooterImpl(shooterController1, shooterController2, shooterHatch);
                    break;
                default:
                    this.shooter = new ShooterImpl(shooterController1, shooterController2, shooterHatch);
                    break;
            }
            start();
        } else if(current.equals(LifecycleEvent.ON_DISABLED)) {
            cancel();
        }
    }

    public void feedBalls() {
        this.feeder.enableHopper();
        this.feeder.enableConveyor();
    }

    public void unfeedBalls() {
        this.feeder.reverseFeeder();
    }

    public void stopFeeder() {
        this.feeder.stopAll();
    }
}
