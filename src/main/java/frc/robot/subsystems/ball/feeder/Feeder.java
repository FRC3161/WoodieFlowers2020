package frc.robot.subsystems.ball.feeder;

import ca.team3161.lib.robot.subsystem.Subsystem;

public interface Feeder extends Subsystem{
    void feedBalls();
    void unload();
    void stop();
}