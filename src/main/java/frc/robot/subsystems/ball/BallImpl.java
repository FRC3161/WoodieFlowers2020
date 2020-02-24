package frc.robot.subsystems.ball;

import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import java.util.concurrent.TimeUnit;

import frc.robot.subsystems.ball.feeder.Feeder;
import frc.robot.subsystems.ball.shooter.Shooter;
import frc.robot.subsystems.ball.intake.Intake;

import ca.team3161.lib.robot.LifecycleEvent;

import frc.robot.subsystems.ball.feeder.FeederImpl;
import frc.robot.subsystems.ball.shooter.ShooterImpl;
import frc.robot.subsystems.ball.intake.IntakeImpl;

public class BallImpl extends RepeatingPooledSubsystem implements Ball {

    private Shooter shooter;
    private Feeder feeder;
    private Intake intake;

    private boolean shootEnabled;

    public BallImpl() {
        // PLACEHOLDER
        super(100, TimeUnit.MILLISECONDS);
        this.shooter = new ShooterImpl();
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
                this.feeder.stopAll();
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
            start();
        } else if(current.equals(LifecycleEvent.ON_DISABLED)) {
            cancel();
        }
    }
}