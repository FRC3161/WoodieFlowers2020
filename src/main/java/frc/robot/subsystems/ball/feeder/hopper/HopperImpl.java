package frc.robot.subsystems.ball.feeder.hopper;

import java.util.concurrent.TimeUnit;

import ca.team3161.lib.robot.LifecycleEvent;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.RobotMap;

public class HopperImpl extends RepeatingPooledSubsystem implements Hopper{

    enum HopperState {
        FEEDING,
        OFF,
        UNLOADING
    }

    WPI_TalonSRX hopperController;
    HopperState state;


    HopperImpl() {
        super(1, TimeUnit.SECONDS);

        this.hopperController = new WPI_TalonSRX(RobotMap.HOPPER_TALON_PORT);
        this.state = HopperState.OFF;
    }

    @Override
    public void task() {
        // Placeholder
    }

    @Override
    public void defineResources() {
        require(this.hopperController);
    }

    @Override
    public void lifecycleStatusChanged(LifecycleEvent previous, LifecycleEvent current) {
        // Placeholder
    }

    @Override
    public void feed() {
        // TODO Auto-generated method stub

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