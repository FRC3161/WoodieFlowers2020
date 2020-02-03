package frc.robot.subsystems.ball;

import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import java.util.concurrent.TimeUnit;

import frc.robot.subsystems.ball.feeder.FeederImpl;
import frc.robot.subsystems.ball.shooter.ShooterImpl;
import frc.robot.subsystems.ball.intake.IntakeImpl;

public class BallImpl extends RepeatingPooledSubsystem implements Ball {

    private ShooterImpl shooter;
    private FeederImpl feeder;
    private IntakeImpl intake;

    private boolean shootEnabled;
    
    BallImpl() {
        // PLACEHOLDER
        super(1, TimeUnit.SECONDS);
        this.shooter = new ShooterImpl();
        this.feeder = new FeederImpl();
        this.intake = new IntakeImpl();

        this.intake.start();
        this.shootEnabled = false;
    }
    
    public void shoot(){
        this.shootEnabled = true;
    }

    public void cancelShooting() {
        this.shootEnabled = false;
    }

    public void stop(){
        this.shooter.stopShooter();
        this.feeder.stop();
        this.shootEnabled = false;
    }

    public void unload() {
        this.feeder.unload();
        // Seems kind of redundant, but abstraction
    }

    public void defineResources(){
        return;
    }

    public void collect() {
        this.intake.extend();
    }

    public void task() {
        if(this.shootEnabled) {
            this.shooter.runShooter();
            if(this.shooter.readyForBalls()) {
                this.feeder.feedBalls();
            }
        }
    }
}