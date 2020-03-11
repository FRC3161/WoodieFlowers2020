package frc.robot;

import frc.robot.subsystems.drivetrain.Drivetrain;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import ca.team3161.lib.robot.TitanBot;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
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

    public void generateTrajectory() {

        Pose2d start = new Pose2d(Units.feetToMeters(10), Units.feetToMeters(10), Rotation2d.fromDegrees(0));
        Pose2d trench = new Pose2d(Units.feetToMeters(20), Units.feetToMeters(3), Rotation2d.fromDegrees(-180)); // Test waypoint

        // Random interior waypoints
        var interiorWaypoints = new ArrayList<Translation2d>();
        interiorWaypoints.add(new Translation2d(Units.feetToMeters(2.6), Units.feetToMeters(1.2)));
        interiorWaypoints.add(new Translation2d(Units.feetToMeters(12.7), Units.feetToMeters(4.6)));

        TrajectoryConfig config = new TrajectoryConfig(Units.feetToMeters(18), Units.feetToMeters(8)); // Basically random values that I vaguely remember ATM

        Trajectory trajectory = TrajectoryGenerator.generateTrajectory(start, interiorWaypoints, trench, config);
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