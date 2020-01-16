package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DrivetrainImpl implements Drivetrain {
    //Speed controllers and drivetrain
    private final WPI_TalonSRX leftMotorController;
    private final WPI_TalonSRX rightMotorController;
    private final DifferentialDrive drivetrain;
    
    //Encoders
    private final Encoder leftEncoder;
    private final Encoder rightEncoder;
    
    //PID
    private final PIDController leftPIDController;
    private final PIDController rightPIDController;
    
    public DrivetrainImpl(){
        //Motor controllers and drivetrain
        this.leftMotorController = new WPI_TalonSRX(frc.robot.RobotMap.TALON_LEFT_DRIVE_PORT); //TODO Placeholder Channel
        this.rightMotorController = new WPI_TalonSRX(frc.robot.RobotMap.TALON_RIGHT_DRIVE_PORT); //TODO Placeholder Channel
        this.drivetrain = new DifferentialDrive(this.leftMotorController, this.rightMotorController);
        //Encoders
        this.leftEncoder = new Encoder(frc.robot.RobotMap.LEFT_ENCODER_PORTS[0], frc.robot.RobotMap.LEFT_ENCODER_PORTS[1]);
        this.rightEncoder = new Encoder(frc.robot.RobotMap.RIGHT_ENCODER_PORTS[0], frc.robot.RobotMap.RIGHT_ENCODER_PORTS[1]);
        //PID
        this.leftPIDController = new PIDController(frc.robot.RobotMap.Kp, frc.robot.RobotMap.Ki, frc.robot.RobotMap.Kd);
        this.rightPIDController = new PIDController(frc.robot.RobotMap.Kp, frc.robot.RobotMap.Ki, frc.robot.RobotMap.Kd);
    }

    @Override
    public void drive(double leftSpeed, double rightSpeed) {
        this.drivetrain.tankDrive(this.leftPIDController.calculate(leftEncoder.getDistance()), this.rightPIDController.calculate(rightEncoder.getDistance())); //Not actual working PID 
    }
}