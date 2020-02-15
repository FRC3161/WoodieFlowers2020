package frc.robot;

import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.ball.Ball;

public class Autonomous {
    
    Drivetrain drivetrain;
    Ball ball;

    static final double WHEEL_DIAMETER = 18.84955592;
    
    Autonomous(Drivetrain driveSubsystem, Ball ballSubsystem) {
        this.drivetrain = driveSubsystem;
        this.ball = ballSubsystem;
    }

    public void hitNRun() throws InterruptedException {
        this.ball.shoot();
        Thread.sleep(5000);
        this.ball.cancelShooting();
        this.drivetrain.setSetpoint(-120 * (WHEEL_DIAMETER * 128)); // Distance in inches * Wheel diameter in inches * ticks per rotation
        while(!this.drivetrain.atSetpoint()){
            this.drivetrain.drivePID();
            Thread.sleep(200);
        }
    }

}