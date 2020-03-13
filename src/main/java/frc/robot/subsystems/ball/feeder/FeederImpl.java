package frc.robot.subsystems.ball.feeder;

import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import edu.wpi.first.wpilibj.Ultrasonic;
import ca.team3161.lib.utils.SmartDashboardTuner;
import java.util.concurrent.TimeUnit;
import frc.robot.subsystems.ball.feeder.conveyor.Conveyor;
import frc.robot.subsystems.ball.feeder.conveyor.ConveyorImpl;
import frc.robot.subsystems.ball.feeder.hopper.Hopper;
import frc.robot.subsystems.ball.feeder.hopper.HopperImpl;
import frc.robot.RobotMap;

public class FeederImpl extends RepeatingPooledSubsystem implements Feeder {

    enum FeederState {
        PRIMING,
        FEEDING,
        OFF,
        UNLOADING
    }
    
    Conveyor conveyorSubsystem;
    Hopper hopperSubsystem;

    double topUltrasonicDistanceMM;
    Ultrasonic topUltrasonic;
    SmartDashboardTuner topUltrasonicTuner;

    UltrasonicPoller topPoller;

    volatile FeederState state;
    volatile boolean shooting;

    public FeederImpl(){
        super(1, TimeUnit.SECONDS);
        
        this.topUltrasonic = new Ultrasonic(RobotMap.ULTRASONIC_PORTS[0], RobotMap.ULTRASONIC_PORTS[1]);
        
        this.conveyorSubsystem = new ConveyorImpl();
        this.hopperSubsystem = new HopperImpl();

        this.topUltrasonicDistanceMM = 30;
        this.topUltrasonicTuner = new SmartDashboardTuner("Top Ultrasonic Tuner", this.topUltrasonicDistanceMM, x -> this.topUltrasonicDistanceMM = x);

        this.topPoller = new UltrasonicPoller(this.topUltrasonic, 3000, 20); // Temp values

    }

    public void task() {
        switch(this.state) {
            case PRIMING:
                if(this.topUltrasonic.getRangeMM() > this.topUltrasonicDistanceMM) {
                    this.enable(FeederComponent.HOPPER);
                    this.enable(FeederComponent.CONVEYOR);
                } else {
                    this.state = FeederState.OFF;
                }
                break; // It will behave as expected because this runs in a loop
            case FEEDING:
                this.enable(FeederComponent.HOPPER);
                if(this.shooting){
                    this.enable(FeederComponent.CONVEYOR);
                } else {
                    this.stop(FeederComponent.CONVEYOR);
                }
                break;
            case UNLOADING:
                if(!this.topPoller.checkUnloaded()) {
                    this.enable(FeederComponent.HOPPER, FeederDirection.REVERSE);
                    this.enable(FeederComponent.CONVEYOR, FeederDirection.REVERSE);
                    break;
                } else {
                    this.state = FeederState.OFF;
                }
            default:
                
        }
    }

    @Override
    public void feed() {
        this.state = FeederState.FEEDING;
    }

    @Override
    public void defineResources() {
        require(this.topUltrasonic);
    }

    @Override
    public void prime() {
        this.state = FeederState.PRIMING;
    }



    @Override
    public void unload() {
        this.state = FeederState.UNLOADING;
    }

    void enable(FeederComponent component, FeederDirection direction) throws RuntimeException{
        switch(component) {
            case HOPPER:
                if(direction == FeederDirection.FORWARDS) {
                    this.conveyorSubsystem.feed();
                } else {
                    this.conveyorSubsystem.unload();
                }
            case CONVEYOR:
                if(direction == FeederDirection.FORWARDS){
                    this.conveyorSubsystem.feed();
                } else {
                    this.conveyorSubsystem.unload();
                }
                break;
            default:
                throw new RuntimeException("Specified component does not exist!");
        }
    }

    void enable(FeederComponent component) {
        this.enable(component, FeederDirection.FORWARDS);
    }

    void stop(FeederComponent component) throws RuntimeException {
        switch(component) {
            case CONVEYOR:
                this.conveyorSubsystem.stop();
            case HOPPER:
                this.hopperSubsystem.stop();
            default:
                throw new RuntimeException("Specified component does not exist!");
        }
    }

    @Override
    public void disable() {
        this.state = FeederState.OFF;
    }

    @Override
    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

}