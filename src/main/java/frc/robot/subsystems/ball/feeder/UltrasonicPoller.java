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
        this.noBalls = true;
        this.startTime = 0;

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

    // Consider renaming, probably want to generalize the name if we ever re-use it
    public boolean checkUnloaded(){
        return this.noBalls;
    }

    @Override
    public void task(){
        this.currentRange = this.ultrasonicSensor.getRangeMM();

        if(this.startTime == 0){
            this.startTime = System.nanoTime(); // I know about a do while, but this isn't contained within a while loop 
        }
        
        if((this.currentRange > this.distance) && (TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this.startTime)  > this.time)) {
            this.noBalls = true;
        } else if(this.currentRange <= this.distance) {
            this.startTime = System.nanoTime();
            this.noBalls = false;
        }
    }
}