package frc.robot.subsystems.ball.feeder;

import java.util.concurrent.TimeUnit;

import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import edu.wpi.first.wpilibj.Ultrasonic;

// Pretty sure this only allows package level access
class UltrasonicPoller extends RepeatingPooledSubsystem {

    Ultrasonic ultrasonicSensor;

    // Consider renaming, couldn't think of a better one ATM
    boolean noBalls;
    long time;
    double distance;
    double currentRange;

    long startTime;

    UltrasonicPoller(Ultrasonic sensor, long time, double distance, long rate) {
        // sensor is pretty self explanatory
        // time is how long you want the sensor to not detect an object until true is returned
        // distance is the minimum distance threshold
        super(rate, TimeUnit.MILLISECONDS); // 4Hz seems reasonable
        this.ultrasonicSensor = sensor;
        this.time = time;
        this.distance = distance;
        this.noBalls = false;
        this.startTime = 0;

    }

    UltrasonicPoller(Ultrasonic sensor, long time, double distance) {
        this(sensor, time, distance, 25);
    }

    @Override
    public void defineResources(){
        require(ultrasonicSensor);
    }

    // Consider renaming, probably want to generalize the name if we ever re-use it
    public boolean checkUnloaded(){
        return this.noBalls;
    }

    @Override
    public void task(){
        this.currentRange = this.ultrasonicSensor.getRangeMM();
        if((this.currentRange > this.distance) && (((System.nanoTime() - this.startTime) / 100000) > this.time)) {
            this.noBalls = true;
        } else if(this.currentRange <= this.distance) {
            this.startTime = System.nanoTime();
        }
    }
}