package frc.robot.subsystems.ball.intake;

import ca.team3161.lib.robot.LifecycleListener;
import ca.team3161.lib.robot.subsystem.Subsystem;

public interface Intake extends Subsystem, LifecycleListener{
    void retract();
    void extend();
    void extend(IntakeImpl.MotorDirections direction);
}