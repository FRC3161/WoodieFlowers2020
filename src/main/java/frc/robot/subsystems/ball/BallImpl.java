package frc.robot.subsystems.ball;

import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import java.util.concurrent.TimeUnit;

import frc.robot.subsystems.ball.feeder.FeederImpl;
import frc.robot.subsystems.ball.shooter.ShooterImpl;

public class BallImpl extends RepeatingPooledSubsystem implements Ball {

    ShooterImpl shooter;
    FeederImpl feeder;
    
    BallImpl() {
        // PLACEHOLDER
        super(1, TimeUnit.SECONDS);
        this.shooter = new ShooterImpl();
        this.feeder = new FeederImpl();
    }
    
    public void shoot(){
        // PLACEHOLDER
        return;
    }

    public void cancelShoot(){
        //PLACEHOLDER
        return;
    }

    public void defineResources(){
        // PLACEHOLDER
        return;
    }

    public void task() {
        // PLACEHOLDER
        return;
    }
}