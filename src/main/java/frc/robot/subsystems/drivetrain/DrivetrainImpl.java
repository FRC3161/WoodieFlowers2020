package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import edu.wpi.first.wpilibj.Encoder;
import java.util.concurrent.TimeUnit;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;

public class DrivetrainImpl extends RepeatingPooledSubsystem implements Drivetrain {
    //Speed controllers and drivetrain
    private final WPI_TalonSRX leftMotorController;
    private final WPI_TalonSRX rightMotorController;
    private final Encoder leftEncoder;
    private final Encoder rightEncoder;
    private final DifferentialDrive drivetrain;
    
    public DrivetrainImpl(){
        super(125, TimeUnit.MILLISECONDS); // Consider adjusting, 125ms might be wasteful in terms of cpu
        //Motor controllers and drivetrain
        this.leftMotorController = new WPI_TalonSRX(RobotMap.TALON_LEFT_DRIVE_PORT);
        this.leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_PORTS[0], RobotMap.LEFT_ENCODER_PORTS[1]);

        this.rightMotorController = new WPI_TalonSRX(RobotMap.TALON_RIGHT_DRIVE_PORT);
        this.rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_PORTS[0], RobotMap.RIGHT_ENCODER_PORTS[1]);

        this.drivetrain = new DifferentialDrive(this.leftMotorController, this.rightMotorController);
    }

    public void defineResources() {
        require(leftMotorController);
        require(rightMotorController);
        require(leftEncoder);
        require(rightEncoder);
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

    public void task(){
        return;
    }
}