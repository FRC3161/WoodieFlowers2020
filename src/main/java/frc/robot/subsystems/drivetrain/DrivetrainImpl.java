package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import edu.wpi.first.wpilibj.Encoder;
import java.util.concurrent.TimeUnit;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.RobotMap;

public class DrivetrainImpl extends RepeatingPooledSubsystem implements Drivetrain {
    //Speed controllers and drivetrain
    
    // Left Side Motor Controllers and Group
    private final WPI_TalonSRX leftMotorController1;
    private final WPI_TalonSRX leftMotorController2;
    private final WPI_TalonSRX leftMotorController3;
    private final SpeedControllerGroup leftMotorControllers;

    // Right Side Motor Controllers and Group
    private final WPI_TalonSRX rightMotorController1;
    private final WPI_TalonSRX rightMotorController2;
    private final WPI_TalonSRX rightMotorController3;
    private final SpeedControllerGroup rightMotorControllers;

    private final Encoder leftEncoder;
    private final Encoder rightEncoder;

    private final PIDController leftPIDController;
    private final PIDController rightPIDController; 

    private final DifferentialDrive drivetrain;
    
    public DrivetrainImpl(){
        super(500, TimeUnit.MILLISECONDS); // Slow because all it does is put ticks to the dashboard for testing purposes 
        //Motor controllers and drivetrain
        this.leftMotorController1 = new WPI_TalonSRX(RobotMap.TALON_LEFT_DRIVE_PORTS[0]);
        this.leftMotorController2 = new WPI_TalonSRX(RobotMap.TALON_LEFT_DRIVE_PORTS[1]);
        this.leftMotorController3 = new WPI_TalonSRX(RobotMap.TALON_LEFT_DRIVE_PORTS[2]);
        this.leftMotorControllers = new SpeedControllerGroup(this.leftMotorController1, this.leftMotorController2, this.leftMotorController3);
        this.leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_PORTS[0], RobotMap.LEFT_ENCODER_PORTS[1]);

        this.rightMotorController1 = new WPI_TalonSRX(RobotMap.TALON_RIGHT_DRIVE_PORTS[0]);
        this.rightMotorController2 = new WPI_TalonSRX(RobotMap.TALON_RIGHT_DRIVE_PORTS[1]);
        this.rightMotorController3 = new WPI_TalonSRX(RobotMap.TALON_RIGHT_DRIVE_PORTS[2]);
        this.rightMotorControllers = new SpeedControllerGroup(this.rightMotorController1, this.rightMotorController2, this.rightMotorController3);
        this.rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_PORTS[0], RobotMap.RIGHT_ENCODER_PORTS[1]);

        this.leftPIDController = new PIDController(RobotMap.DRIVETRAIN_KP, RobotMap.DRIVETRAIN_KI, RobotMap.DRIVETRAIN_KD); // TODO setup SmartdashboardPIDTuner
        this.rightPIDController = new PIDController(RobotMap.DRIVETRAIN_KP, RobotMap.DRIVETRAIN_KI, RobotMap.DRIVETRAIN_KD);

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
        return this.leftPIDController.atSetpoint() & rightPIDController.atSetpoint();
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
    public void drive(double leftSpeed, double rightSpeed) {
        this.drivetrain.tankDrive(leftSpeed, rightSpeed);
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