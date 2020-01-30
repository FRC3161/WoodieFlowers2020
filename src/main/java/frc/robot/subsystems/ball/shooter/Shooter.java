package frc.robot.subsystems.ball.shooter;

public interface Shooter {
    void runShooter();
    void invertPosition();
    boolean readyForBalls();
}