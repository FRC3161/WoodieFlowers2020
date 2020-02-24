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

    boolean firstRun;

    long startTime;

    UltrasonicPoller(Ultrasonic sensor, long time, double distance, long rate) {
        // sensor is pretty self explanatory
        // time is how long you want the sensor to not detect an object until true is returned
        // distance is the minimum distance threshold
        super(rate, TimeUnit.MILLISECONDS); // 4Hz seems reasonable
        this.ultrasonicSensor = sensor;
        this.time = time;
        this.distance = distance;

        this.firstRun = true; // Not sure if this should be static
    }

    UltrasonicPoller(Ultrasonic sensor, long time, double distance) {
        this(sensor, time, distance, 25);
    }

    @Override
    public void defineResources(){
        require(ultrasonicSensor);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setDistance(double distance){
        this.distance = distance;
    }
    
    public boolean checkUnloaded() {
        return this.noBalls;
    }

    @Override
    public void task(){
        if(firstRun) {
            this.startTime = System.nanoTime();
        }
        if(this.ultrasonicSensor.getRangeMM() > this.distance) {
            if(this.startTime > TimeUnit.NANOSECONDS.toMillis(System.nanoTime())) {
                this.noBalls = true;
            }
        } else {
            this.noBalls = false;
        }
    }
}