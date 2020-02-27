package frc.robot.subsystems.ball.feeder;

import ca.team3161.lib.robot.subsystem.Subsystem;

public interface Feeder extends Subsystem{
    void enableConveyor();
    void enableHopper();
    void unload() throws InterruptedException;
    void stopAll();
    void stopConveyor();
    void stopHopper();
    void reverseFeeder();
}