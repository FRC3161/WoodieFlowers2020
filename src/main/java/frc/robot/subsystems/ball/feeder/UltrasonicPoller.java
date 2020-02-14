package frc.robot.subsystems.ball.feeder;

import edu.wpi.first.wpilibj.Ultrasonic;

// Pretty sure this only allows package level access
class UltrasonicPoller {

    Ultrasonic ultrasonicSensor;

    UltrasonicPoller(Ultrasonic sensor) {
        this.ultrasonicSensor = sensor;
    }
}