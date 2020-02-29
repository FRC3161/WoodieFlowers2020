package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import ca.team3161.lib.robot.pid.RampingSpeedController;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import edu.wpi.first.wpilibj.Encoder;
import java.util.concurrent.TimeUnit;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.RobotMap;

public class DrivetrainImpl extends RepeatingPooledSubsystem implements Drivetrain {
    //Speed controllers and drivetrain
    
    // Left Side Motor Controllers and Group
    private final RampingSpeedController leftMotorController1;
    private final RampingSpeedController leftMotorController2;
    private final RampingSpeedController leftMotorController3;
    private final SpeedControllerGroup leftMotorControllers;

    // PID Constants
    static final double Kp = 10;
    static final double Ki = 0.1;
    static final double Kd = 0.1;

    // Right Side Motor Controllers and Group
    
    private final RampingSpeedController rightMotorController1;
    private final RampingSpeedController rightMotorController2;
    private final RampingSpeedController rightMotorController3;
    private final SpeedControllerGroup rightMotorControllers;
    
    private final Encoder leftEncoder;
    private final Encoder rightEncoder;

    private final PIDController leftPIDController;
    private final PIDController rightPIDController; 

    private final DifferentialDrive drivetrain;
    
    public DrivetrainImpl(){
        super(500, TimeUnit.MILLISECONDS); // Slow because all it does is put ticks to the dashboard for testing purposes 
        //Motor controllers and drivetrain
        this.leftMotorController1 = new RampingSpeedController.Builder()
            .controller(new WPI_TalonSRX(RobotMap.TALON_LEFT_DRIVE_PORTS[0]))
            .maxStep(1)
            .rampRatio(1.1)
            .firstFilter(0.1)
            .secondFilter(0.05)
            .build(); 

        this.leftMotorController2 = new RampingSpeedController.Builder()
            .controller(new WPI_VictorSPX(RobotMap.TALON_LEFT_DRIVE_PORTS[1]))
            .maxStep(1)
            .rampRatio(1.1)
            .firstFilter(0.1)
            .secondFilter(0.05)
            .build(); 

        this.leftMotorController3 = new RampingSpeedController.Builder()
            .controller(new WPI_VictorSPX(RobotMap.TALON_LEFT_DRIVE_PORTS[2]))
            .maxStep(1)
            .rampRatio(1.1)
            .firstFilter(0.1)
            .secondFilter(0.05)
            .build(); 

        this.leftMotorControllers = new SpeedControllerGroup(this.leftMotorController1, this.leftMotorController2, this.leftMotorController3);
        this.leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_PORTS[0], RobotMap.LEFT_ENCODER_PORTS[1]);

        this.rightMotorController1 = new RampingSpeedController.Builder()
                .controller(new WPI_TalonSRX(RobotMap.TALON_RIGHT_DRIVE_PORTS[0]))
                .maxStep(1)
                .rampRatio(1.1)
                .firstFilter(0.1)
                .secondFilter(0.05)
                .build(); 

        this.rightMotorController2 = new RampingSpeedController.Builder()
                .controller(new WPI_VictorSPX(RobotMap.TALON_RIGHT_DRIVE_PORTS[1]))
                .maxStep(1)
                .rampRatio(1.1)
                .firstFilter(0.1)
                .secondFilter(0.05)
                .build(); 

        this.rightMotorController3 = new RampingSpeedController.Builder()
            .controller(new WPI_VictorSPX(RobotMap.TALON_RIGHT_DRIVE_PORTS[2]))
            .maxStep(1)
            .rampRatio(1.1)
            .firstFilter(0.1)
            .secondFilter(0.05)
            .build(); 

        this.rightMotorControllers = new SpeedControllerGroup(this.rightMotorController1, this.rightMotorController2, this.rightMotorController3);
        this.rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_PORTS[0], RobotMap.RIGHT_ENCODER_PORTS[1]);

        this.leftPIDController = new PIDController(Kp, Ki, Kd); // TODO setup SmartdashboardPIDTuner
        this.rightPIDController = new PIDController(Kp, Ki, Kd);

        this.drivetrain = new DifferentialDrive(this.leftMotorControllers, this.rightMotorControllers);
    }

    @Override
    public void setSetpoint(double setpoint){
        this.leftPIDController.setSetpoint(setpoint);
        this.rightPIDController.setSetpoint(setpoint);
    }

    @Override
    public void drivePID(){
        this.drivetrain.tankDrive(this.leftPIDController.calculate(this.leftEncoder.get()), this.rightPIDController.calculate(this.rightEncoder.get())); // Consider doing something to make this more reasonable
    }

    @Override
    public boolean atSetpoint(){
        //return this.leftPIDController.atSetpoint() && rightPIDController.atSetpoint();
        return !this.rightPIDController.atSetpoint();
    }

    public void defineResources() {
        
        require(leftMotorController1);
        require(leftMotorController2);
        require(leftMotorController3);
        require(leftMotorControllers);
        
        require(rightMotorController1);
        require(rightMotorController2);
        require(rightMotorController3);
        require(rightMotorControllers);

        require(leftEncoder);
        require(rightEncoder);
    }

    @Override
    public void driveTank(double leftSpeed, double rightSpeed) {
        this.drivetrain.tankDrive(-leftSpeed, -rightSpeed);
    }

    @Override
    public void driveArcade(double xSpeed, double zRotation){
        this.drivetrain.arcadeDrive(-xSpeed, zRotation);
    }

    public long getLeftEncoderTicks() {
        return this.leftEncoder.get();
    }

    public long getRightEncoderTicks() {
        return this.rightEncoder.get();
    }

    public void resetEncoderTicks() {
        this.leftEncoder.reset();
        this.rightEncoder.reset();
    }

    public void task(){
        SmartDashboard.putNumber("Left Side Encoder Ticks", this.getLeftEncoderTicks());
        SmartDashboard.putNumber("Right Side Encoder Ticks", this.getRightEncoderTicks());
    }
}