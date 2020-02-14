package frc.robot.subsystems.ball.feeder;

import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.time.StopWatch;

import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import edu.wpi.first.wpilibj.Ultrasonic;

// Pretty sure this only allows package level access
class UltrasonicPoller extends RepeatingPooledSubsystem {

    Ultrasonic ultrasonicSensor;
    StopWatch timer = new StopWatch();

    UltrasonicPoller(Ultrasonic sensor, int time, double distance) {
        // sensor is pretty self explanatory
        // time is how long you want the sensor to not detect an object until true is returned
        // distance is the minimum distance threshold
        super(25, TimeUnit.MILLISECONDS); // 4Hz seems reasonable
        this.ultrasonicSensor = sensor;
    }

    @Override
    public void defineResources(){
        require(ultrasonicSensor);
    }

    @Override
    public void task(){
        // Placholder
        return;
    }
}