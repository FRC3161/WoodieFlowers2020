package frc.robot;

public final class RobotMap {
    // Drive
    public static final int TALON_LEFT_DRIVE_PORT = 0; //TODO
    public static final int TALON_RIGHT_DRIVE_PORT = 1; //TODO
    public static final int DRIVER_PAD_PORT = 0;

    // Encoders
    public static final int[] LEFT_ENCODER_PORTS = {0, 1}; //TODO
    public static final int[] RIGHT_ENCODER_PORTS = {2, 3}; //TODO

    // PID
    public static final double Kp = 0.0001;
    public static final double Ki = 0.0001;
    public static final double Kd = 0.0001;

    // Shooter
    public static final int SHOOTER_TALON_PORT = 2; // TODO
    public static final int ROLLER_TALON_PORT = 3;
    public static final int BELT_TALON_PORT = 4;
    public static final int SHOOTER_SOLENOID_CHANNEL = 0; //TODO
    public static final int[] SHOOTER_ENCODER_PORTS = {4, 5};

    // Climber
    public static final int CLIMBER_TALON_PORT = 3; // TODO
}