package frc.robot.subsystems.ball;

import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import java.util.concurrent.TimeUnit;

import frc.robot.subsystems.ball.feeder.FeederImpl;
import frc.robot.subsystems.ball.shooter.ShooterImpl;

public class BallImpl extends RepeatingPooledSubsystem implements Ball {

    private ShooterImpl shooter;
    private FeederImpl feeder;
    
    BallImpl() {
        // PLACEHOLDER
        super(1, TimeUnit.SECONDS);
        this.shooter = new ShooterImpl();
        this.feeder = new FeederImpl();
    }
    
    public void shoot(){
        this.shooter.runShooter();
        if(this.shooter.readyForBalls()){
            this.feeder.feedBalls();
        }
    }

    public void cancelShoot(){
        this.shooter.stopShooter();
        this.feeder.stop();
    }

    public void unload() {
        this.feeder.unload();
        // Seems kind of redundant, but abstraction
    }

    public void defineResources(){
        return;
        // IDK if I need to require the shooter and feeder or not
    }

    public void task() {
        // PLACEHOLDER
        return;
    }
}