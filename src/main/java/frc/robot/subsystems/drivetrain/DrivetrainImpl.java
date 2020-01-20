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
    
    public DrivetrainImpl(){
        //Motor controllers and drivetrain
        this.leftMotorController = new WPI_TalonSRX(frc.robot.RobotMap.TALON_LEFT_DRIVE_PORT); //TODO Placeholder Channel
        this.rightMotorController = new WPI_TalonSRX(frc.robot.RobotMap.TALON_RIGHT_DRIVE_PORT); //TODO Placeholder Channel
        this.drivetrain = new DifferentialDrive(this.leftMotorController, this.rightMotorController);
    }

    @Override
    public void drive(double leftSpeed, double rightSpeed) {
        this.drivetrain.tankDrive(leftSpeed, rightSpeed);
    }
}