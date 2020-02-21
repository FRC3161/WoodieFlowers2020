package frc.robot.subsystems.ball.shooter;

import ca.team3161.lib.robot.LifecycleListener;
import ca.team3161.lib.robot.subsystem.Subsystem;

public interface Shooter extends Subsystem, LifecycleListener{
    void runShooter();
    void stopShooter();
    boolean readyForBalls();
}