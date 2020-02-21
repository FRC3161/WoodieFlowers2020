package frc.robot;

public final class RobotMap {
    // TODO gotta fix all of these, it's pretty much just random numbers at this point
    
    // Drive
    public static final int[] TALON_LEFT_DRIVE_PORTS = {10, 2, 8}; 
    public static final int[] TALON_RIGHT_DRIVE_PORTS = {9, 5, 6}; 
    public static final int DRIVER_PAD_PORT = 0;
    public static final int OPERATOR_PAD_PORT = 1;

    // Encoders
    public static final int[] LEFT_ENCODER_PORTS = {2, 3}; 
    public static final int[] RIGHT_ENCODER_PORTS = {1, 0}; 

    // Shooter
    public static final int[] SHOOTER_TALON_PORTS = {2, 11}; 
    public static final int ROLLER_TALON_PORT = 0;
    public static final int BELT_TALON_PORT = 1;
    public static final int[] ULTRASONIC_PORTS = {7,8}; // {IN, OUT}
    public static final int[] SHOOTER_SOLENOID_CHANNELS = {7, 8}; 
    public static final int[] SHOOTER_ENCODER_PORTS = {4, 5};

    // Intake
    public static final int INTAKE_TALON_PORT = 4;
    public static final int INTAKE_SOLENOID_IN_CHANNEL = 0;
    public static final int INTAKE_SOLENOID_OUT_CHANNEL = 3;

    // Climber
    public static final int CLIMBER_TALON_PORT = 3; 
    public static final int LIFTER_TALON_PORT = 5;
    public static final int CLIMBER_SOLENOID1_CHANNEL = 1;
    public static final int CLIMBER_SOLENOID2_CHANNEL = 2;
}