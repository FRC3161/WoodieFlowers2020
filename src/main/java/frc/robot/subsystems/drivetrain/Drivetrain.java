package frc.robot.subsystems.drivetrain;

import ca.team3161.lib.robot.LifecycleListener;
import ca.team3161.lib.robot.subsystem.Subsystem;
import edu.wpi.first.wpilibj.geometry.Pose2d;

public interface Drivetrain extends Subsystem, LifecycleListener {
    void driveTank(double leftSpeed, double rightSpeed);
    void driveArcade(double xSpeed, double zRotation);
    void setSetpoint(double setpoint);
    void drivePID();
    boolean atSetpoint();
    void resetEncoderTicks();
    Pose2d getPose();
}