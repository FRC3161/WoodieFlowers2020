package frc.robot.subsystems.ball.shooter;

public interface Shooter {
    void shoot(boolean shooting);
    void invertPosition();
    boolean readyForBalls();
}