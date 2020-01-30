package frc.robot.subsystems.ball.shooter;

public interface Shooter {
    void runShooter();
    void stopShooter();
    void invertPosition();
    boolean readyForBalls();
}