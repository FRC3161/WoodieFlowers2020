package frc.robot;

import frc.robot.subsystems.drivetrain.Drivetrain;

import java.util.concurrent.TimeUnit;

import ca.team3161.lib.robot.TitanBot;
import frc.robot.subsystems.ball.Ball;

public class Autonomous {
    
    TitanBot bot;
    Drivetrain drive;
    Ball ball;

    static final double WHEEL_DIAMETER = 18.84955592;
    
    Autonomous(TitanBot bot, Drivetrain driveSubsystem, Ball ballSubsystem) {
        this.drive = driveSubsystem;
        this.ball = ballSubsystem;
        this.bot = bot;
    }

    public void hitNRun() throws InterruptedException {
        this.ball.shoot();
        Thread.sleep(5000);
        this.ball.cancelShooting();
        this.drive.setSetpoint((-120 / WHEEL_DIAMETER) * 128); // Distance in inches / Wheel diameter in inches * ticks per rotation
        while(!this.drive.atSetpoint()){
            this.drive.drivePID();
            Thread.sleep(20);
        }
    }

    public void wallShothitNRun() throws InterruptedException {
        // Drive forwards to goal
        this.drive.driveArcade(-0.4, 0);
        this.bot.waitFor(3, TimeUnit.SECONDS);
        this.drive.driveArcade(0, 0);
        // Shoot
        this.ball.shoot();
        this.bot.waitFor(4, TimeUnit.SECONDS);
        this.bot.waitFor(500, TimeUnit.MILLISECONDS);
        this.ball.cancelShooting();
        // Drive away
        this.drive.driveArcade(0.3, 0);
        this.bot.waitFor(4, TimeUnit.SECONDS);
        this.drive.driveArcade(0, 0);
    }

    public void driveAway() throws InterruptedException{
        // Drive away
        this.drive.driveArcade(0.3, 0);
        this.bot.waitFor(2, TimeUnit.SECONDS);
        this.drive.driveArcade(0, 0);
    }

    public void waitAndShoot() throws InterruptedException {
        // Wait
        this.bot.waitFor(8, TimeUnit.SECONDS);
        // Drive to goal
        this.drive.driveArcade(-0.4, 0);
        this.bot.waitFor(3, TimeUnit.SECONDS);
        // Shoot
        this.ball.shoot();
        this.bot.waitFor(3, TimeUnit.SECONDS);
        this.ball.cancelShooting();
    }

    public void wallShoot() throws InterruptedException{
        // Drive to wall
        this.drive.driveArcade(-0.4, 0);
        this.bot.waitFor(3, TimeUnit.SECONDS);
        this.drive.driveArcade(0, 0);
        // Shoot
        this.ball.shoot();
        this.bot.waitFor(4, TimeUnit.SECONDS);
        this.bot.waitFor(500, TimeUnit.MILLISECONDS);
        this.ball.cancelShooting();
    }

    }