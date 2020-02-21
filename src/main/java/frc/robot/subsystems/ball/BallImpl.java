package frc.robot.subsystems.ball;

import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import java.util.concurrent.TimeUnit;

import frc.robot.subsystems.ball.feeder.Feeder;
import frc.robot.subsystems.ball.shooter.Shooter;
import frc.robot.subsystems.ball.intake.Intake;

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
        this.feeder.stop();
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
            if(this.shooter.readyForBalls()) {
                this.feeder.feedBalls();
            } else {
                this.feeder.stop();
            }
        } else {
            this.shooter.stopShooter();
            this.feeder.stop();
        }
    }
}