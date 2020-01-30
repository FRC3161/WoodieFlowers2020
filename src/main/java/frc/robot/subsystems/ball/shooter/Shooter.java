package frc.robot.subsystems.ball.shooter;

public interface Shooter {
    void shoot();
    void invertPosition();
    boolean readyForBalls();
}