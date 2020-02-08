package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;

public class DrivetrainImpl implements Drivetrain {
    //Speed controllers and drivetrain
    private final WPI_TalonSRX leftMotorController;
    private final WPI_TalonSRX rightMotorController;
    private final Encoder leftEncoder;
    private final Encoder rightEncoder;
    private final DifferentialDrive drivetrain;
    
    public DrivetrainImpl(){
        //Motor controllers and drivetrain
        this.leftMotorController = new WPI_TalonSRX(RobotMap.TALON_LEFT_DRIVE_PORT);
        this.leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_PORTS[0], RobotMap.LEFT_ENCODER_PORTS[1]);

        this.rightMotorController = new WPI_TalonSRX(RobotMap.TALON_RIGHT_DRIVE_PORT);
        this.rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_PORTS[0], RobotMap.RIGHT_ENCODER_PORTS[1]);

        this.drivetrain = new DifferentialDrive(this.leftMotorController, this.rightMotorController);
    }

    @Override
    public void drive(double leftSpeed, double rightSpeed) {
        this.drivetrain.tankDrive(leftSpeed, rightSpeed);
    }

    public void getLeftEncoderTicks() {
        this.leftEncoder.get();
    }

    public void getRightEncoderTicks() {
        this.rightEncoder.get();
    }
}