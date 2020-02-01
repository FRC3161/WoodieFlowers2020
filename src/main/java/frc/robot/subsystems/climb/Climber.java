package frc.robot.subsystems.climb;

public interface Climber {
    public void move(char direction);
    void extendClimber();
    void retractClimber();
    void liftRobot();
}