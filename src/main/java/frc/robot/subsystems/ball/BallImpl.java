package frc.robot.subsystems.ball;

import java.util.concurrent.TimeUnit;

import ca.team3161.lib.robot.LifecycleEvent;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import frc.robot.subsystems.ball.feeder.Feeder;
import frc.robot.subsystems.ball.feeder.Feeder.FeederComponent;
import frc.robot.subsystems.ball.feeder.Feeder.FeederDirection;
import frc.robot.subsystems.ball.feeder.FeederImpl;
import frc.robot.subsystems.ball.intake.Intake;
import frc.robot.subsystems.ball.intake.IntakeImpl;
import frc.robot.subsystems.ball.intake.IntakeImpl.MotorDirections;
import frc.robot.subsystems.ball.shooter.Shooter;
import frc.robot.subsystems.ball.shooter.ShooterImpl;

public class BallImpl extends RepeatingPooledSubsystem implements Ball {

    private Shooter shooter;
    private Feeder feeder;
    private Intake intake;

    private FeederStates feeding;

    private boolean shootEnabled;

    public enum FeederStates {
        OFF,
        DOWN,
        UP
    }

    public BallImpl() {
        // PLACEHOLDER
        super(10, TimeUnit.MILLISECONDS);

        this.shooter = new ShooterImpl();
        this.feeder = new FeederImpl();
        this.intake = new IntakeImpl();

        this.intake.start();
        this.shooter.start();
        this.shootEnabled = false;

        this.feeding = FeederStates.OFF;
    }

    public void shoot() {
        this.shootEnabled = true;
    }

    public void cancelShooting() {
        this.shootEnabled = false;
    }

    public void stop() {
        this.shootEnabled = false;
        this.feeding = FeederStates.OFF;
    }

    public void unload() {
        this.feeding = FeederStates.DOWN;
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
            this.feeder.enable(FeederComponent.HOPPER);
            if(this.shooter.readyForBalls()) {
                this.feeder.enable(FeederComponent.CONVEYOR);
            } else {
                this.feeder.stop(FeederComponent.CONVEYOR);
            }
        } else {
            if(this.feeding == FeederStates.OFF){
                this.shooter.stopShooter();
                this.feeder.stop(FeederComponent.CONVEYOR);
                this.feeder.stop(FeederComponent.HOPPER);
            } else if(this.feeding == FeederStates.UP) {
                this.feeder.enable(FeederComponent.CONVEYOR);
                this.feeder.enable(FeederComponent.HOPPER);
            } else {
                this.feeder.enable(FeederComponent.CONVEYOR, FeederDirection.REVERSE);
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
                break;
            default:
                cancel();
        }

    }

   @Override
    public void feedCancel() {
       this.feeding = FeederStates.OFF; 
    }

    @Override
    public void prime() {
        this.feeding = FeederStates.UP;
    }
}
