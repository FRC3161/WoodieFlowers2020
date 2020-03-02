package frc.robot.subsystems.ball;

import java.util.concurrent.TimeUnit;

import ca.team3161.lib.robot.LifecycleEvent;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import frc.robot.subsystems.ball.feeder.Feeder;
import frc.robot.subsystems.ball.feeder.FeederImpl;
import frc.robot.subsystems.ball.intake.Intake;
import frc.robot.subsystems.ball.intake.IntakeImpl;
import frc.robot.subsystems.ball.intake.IntakeImpl.MotorDirections;
import frc.robot.subsystems.ball.shooter.Shooter;
import frc.robot.subsystems.ball.shooter.ShooterImpl;

public class BallImpl extends RepeatingPooledSubsystem implements Ball {

    static final String PIDF_CONTROLLER_STRATEGY = "pidf";
    static final String BANGBANG_CONTROLLER_STRATEGY = "bangbang";

    private Shooter shooter;
    private Feeder feeder;
    private Intake intake;

    private int feeding;

    private boolean shootEnabled;

    public BallImpl() {
        // PLACEHOLDER
        super(10, TimeUnit.MILLISECONDS);

        this.shooter = new ShooterImpl();
        this.feeder = new FeederImpl();
        this.intake = new IntakeImpl();

        this.intake.start();
        this.shooter.start();
        this.shootEnabled = false;

        this.feeding = 0;
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
        this.feeding = -1;
    }
        // Seems kind of redundant, but abstraction

    public void defineResources(){
        return;
    }

    public void collect(boolean direction) {
        if(!direction) {
            this.intake.extend(MotorDirections.BACKWARDS);
        } else {
            this.intake.extend(MotorDirections.FORWARDS);
        }
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
            if(this.feeding == 0){
                this.shooter.stopShooter();
                this.feeder.stopAll();
            } else if(this.feeding == 1) {
                this.feeder.enableConveyor();
                this.feeder.enableHopper();
            } else {
                this.feeder.reverseFeeder();
            }
        }
    }

    @Override
    public void lifecycleStatusChanged(LifecycleEvent previous, LifecycleEvent current) {
        this.shooter.lifecycleStatusChanged(previous, current);
        this.intake.lifecycleStatusChanged(previous, current);

        switch(current) {
            case ON_AUTO:
            case ON_TELEOP:
                start();
            default:
                cancel();
        }

        if(current.equals(LifecycleEvent.ON_AUTO) || current.equals(LifecycleEvent.ON_TELEOP)){

            start();
            }
         else if(current.equals(LifecycleEvent.ON_DISABLED)) {
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

   @Override
    public void feedCancel() {
       this.feeding = 0; 
    }

    @Override
    public void prime() {
        this.feeding = 1;
    }
}
