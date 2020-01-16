package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
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
    
    public DrivetrainImpl(){
        this.leftMotorController = new WPI_TalonSRX(frc.robot.RobotMap.TALON_LEFT_DRIVE_PORT); //TODO Placeholder Channel
        this.rightMotorController = new WPI_TalonSRX(frc.robot.RobotMap.TALON_RIGHT_DRIVE_PORT); //TODO Placeholder Channel
        this.drivetrain = new DifferentialDrive(this.leftMotorController, this.rightMotorController);
        this.leftEncoder = new Encoder(frc.robot.RobotMap.LEFT_ENCODER_PORTS[0], frc.robot.RobotMap.LEFT_ENCODER_PORTS[1]);
        this.rightEncoder = new Encoder(frc.robot.RobotMap.RIGHT_ENCODER_PORTS[0], frc.robot.RobotMap.RIGHT_ENCODER_PORTS[1]);
    }

    @Override
    public void drive(double leftSpeed, double rightSpeed) {
       this.drivetrain.tankDrive(leftSpeed, rightSpeed); 
    }
}