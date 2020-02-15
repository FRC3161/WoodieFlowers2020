package frc.robot;

import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.ball.Ball;

public class Autonomous {
    
    Drivetrain drivetrain;
    Ball ball;
    
    Autonomous(Drivetrain driveSubsystem, Ball ballSubsystem) {
        this.drivetrain = driveSubsystem;
        this.ball = ballSubsystem;
    }

    public void hitNRun() {

    }

}