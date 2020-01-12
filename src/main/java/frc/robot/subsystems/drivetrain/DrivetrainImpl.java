package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DrivetrainImpl implements Drivetrain {
    private final WPI_TalonSRX leftMotorController;
    private final WPI_TalonSRX rightMotorController;
    private final DifferentialDrive drivetrain;
    
    public DrivetrainImpl(){
        this.leftMotorController = new WPI_TalonSRX(RobotMap.TALON_LEFT_DRIVE_PORT); //TODO Placeholder Channel
        this.rightMotorController = new WPI_TalonSRX(RobotMap.TALON_RIGHT_DRIVE_PORT); //TODO Placeholder Channel
        this.drivetrain = new DifferentialDrive(this.leftMotorController, this.rightMotorController);
    }

    @Override
    public void drive(double leftSpeed, double rightSpeed) {
       this.drivetrain.tankDrive(leftSpeed, rightSpeed); 
    }
}