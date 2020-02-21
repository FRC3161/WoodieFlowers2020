package frc.robot.subsystems.ball.shooter;

import ca.team3161.lib.robot.subsystem.Subsystem;

public interface Shooter extends Subsystem{
    void runShooter();
    void stopShooter();
    boolean getHatch();
    void setHatch(boolean position);
    boolean readyForBalls();
}