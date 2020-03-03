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
        UNLOADING
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
        // TODO Auto-generated method stub

    }

    @Override
    public void lifecycleStatusChanged(LifecycleEvent previous, LifecycleEvent current) {
        // TODO Auto-generated method stub

    }

    @Override
    public void feed() {
        // TODO Auto-generated method stub

    }

    @Override
    public void prime() {
        // TODO right now this is the same as feed, but it will be updated to use ultrasonic later
        this.state = ConveyorState.FEEDING;
    }

    @Override
    public void unload() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }

}