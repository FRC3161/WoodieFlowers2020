package frc.robot.subsystems.drivetrain;

import ca.team3161.lib.robot.subsystem.Subsystem;

public interface Drivetrain extends Subsystem{
    void drive(double leftSpeed, double rightSpeed);
    void setSetpoint(double setpoint);
    void drivePID();
    boolean atSetpoint();
    void resetEncoderTicks();
}