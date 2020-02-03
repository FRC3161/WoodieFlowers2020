package frc.robot.subsystems.ball;

public interface Ball {
    void shoot();
    void cancelShooting();
    void stop();
    void unload();
    void collect();
    void retract();
}