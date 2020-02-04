package frc.robot.subsystems.ball.intake;

public interface Intake {
    void retract();
    void extend(IntakeImpl.motorDirections direction);
}