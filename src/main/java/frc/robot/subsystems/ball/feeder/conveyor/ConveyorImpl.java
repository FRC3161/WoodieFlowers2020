package frc.robot.subsystems.ball.feeder.conveyor;

import java.util.concurrent.TimeUnit;
import ca.team3161.lib.robot.LifecycleEvent;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.RobotMap;


public class ConveyorImpl extends RepeatingPooledSubsystem implements Conveyor {

    public enum ConveyorState {
        FEEDING,
        OFF,
        UNLOADING,
        PRIMING
    }

    WPI_TalonSRX conveyorController;
    volatile ConveyorState state;

    ConveyorImpl() {
        super(1, TimeUnit.SECONDS);
        this.conveyorController = new WPI_TalonSRX(RobotMap.BELT_TALON_PORT);
        this.conveyorController.setInverted(true);
        this.state = ConveyorState.OFF;
    }

    @Override
    public void defineResources() {
        require(this.conveyorController);
    }

    @Override
    public void task() {
        if(this.state == ConveyorState.FEEDING) {
            this.conveyorController.set(0.95d);
        } else if(this.state == ConveyorState.UNLOADING) {
            this.conveyorController.set(-0.95d);
        } else if(this.state == ConveyorState.PRIMING){
            // TODO ultrasonic sensor
            this.conveyorController.set(-0.95d);
        } else {
            this.conveyorController.set(0.0d);
        }
    }

    @Override
    public void lifecycleStatusChanged(LifecycleEvent previous, LifecycleEvent current) {
        switch(current) {
            case ON_AUTO:
            case ON_TELEOP:
            case ON_TEST:
                this.start();
                break;
            default:
                this.cancel();
        }
    }

    @Override
    public void feed() {
        this.state = ConveyorState.FEEDING;
    }

    @Override
    public void prime() {
        this.state = ConveyorState.PRIMING;
    }

    @Override
    public void unload() {
        this.state = ConveyorState.UNLOADING;
    }

    @Override
    public void stop() {
        this.state = ConveyorState.OFF;
    }

}