package frc.robot.subsystems.ball.feeder.hopper;

import java.util.concurrent.TimeUnit;

import ca.team3161.lib.robot.LifecycleEvent;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;

public class HopperImpl extends RepeatingPooledSubsystem implements Hopper{

    enum HopperState {
        FEEDING,
        OFF,
        UNLOADING
    }

    HopperImpl() {
        super(1, TimeUnit.SECONDS);
        // Placeholder
    }

    @Override
    public void task() {
        // Placeholder
    }

    @Override
    public void defineResources() {
        // Placeholder
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