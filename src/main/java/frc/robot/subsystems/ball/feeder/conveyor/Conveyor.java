package frc.robot.subsystems.ball.feeder.conveyor;

import ca.team3161.lib.robot.LifecycleListener;
import ca.team3161.lib.robot.subsystem.Subsystem;

public interface Conveyor extends Subsystem, LifecycleListener {
    void feed();
    void prime();
    void unload();
    void stop();
}