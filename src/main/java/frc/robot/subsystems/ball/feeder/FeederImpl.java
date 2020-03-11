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
    
    Conveyor conveyorSubsystem;
    Hopper hopperSubsystem;

    double topUltrasonicDistanceMM;
    Ultrasonic topUltrasonic;
    SmartDashboardTuner topUltrasonicTuner;

    UltrasonicPoller topPoller;

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
        // PLACEHOLDER
        return;
    }

    @Override
    public void defineResources() {
        require(this.topUltrasonic);
    }

    @Override
    public void prime() {
        if(this.topUltrasonic.getRangeMM() > this.topUltrasonicDistanceMM) {
            this.enable(FeederComponent.HOPPER);
            this.enable(FeederComponent.CONVEYOR);
        } else {
            this.stop(FeederComponent.HOPPER);
            this.stop(FeederComponent.CONVEYOR);
        }
    }

    @Override
    public void unload() {
        if(!this.topPoller.checkUnloaded()) {
            this.enable(FeederComponent.HOPPER);
            this.enable(FeederComponent.CONVEYOR);
        } else {
            this.stop(FeederComponent.HOPPER);
            this.stop(FeederComponent.CONVEYOR);
        }
    }

    @Override
    public void enable(FeederComponent component, FeederDirection direction) {
        // Probably shouldn't have a default case IMO
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
        }
    }

    @Override
    public void enable(FeederComponent component) {
        this.enable(component, FeederDirection.FORWARDS);
    }

    @Override
    public void stop(FeederComponent component) {
        // See above
        switch(component) {
            case HOPPER:
                this.hopperSubsystem.stop();
                break;
            case CONVEYOR:
                this.conveyorSubsystem.stop();
                break;
        }
    }

}