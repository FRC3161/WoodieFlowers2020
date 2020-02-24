package frc.robot.subsystems.ball.feeder;

import ca.team3161.lib.robot.subsystem.Subsystem;

public interface Feeder extends Subsystem{
    void setConveyor();
    void setHopper();
    void unload() throws InterruptedException;
    void stop();
}