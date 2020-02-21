package frc.robot.subsystems.ball.shooter;

import ca.team3161.lib.robot.subsystem.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public interface Shooter extends Subsystem{
    void runShooter();
    void stopShooter();
    Value getHatch();
    void setHatch(Value position);
    boolean readyForBalls();
}