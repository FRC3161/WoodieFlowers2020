package frc.robot.subsystems.ball;

import ca.team3161.lib.robot.LifecycleListener;
import ca.team3161.lib.robot.subsystem.Subsystem;

public interface Ball extends Subsystem, LifecycleListener{
    void shoot();
    void cancelShooting();
    void stop();
    void collect();
    void collect(boolean direction);
    void retract();
    void feedBalls();
    void unfeedBalls();
    void stopFeeder();
    void prime();
    void unload();
    void feedCancel();
}