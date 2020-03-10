package frc.robot.subsystems.ball.feeder;

import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import edu.wpi.first.wpilibj.Ultrasonic;
import frc.robot.subsystems.ball.feeder.UltrasonicPoller;
import ca.team3161.lib.utils.SmartDashboardTuner;
import java.util.concurrent.TimeUnit;
import frc.robot.RobotMap;

public class FeederImpl extends RepeatingPooledSubsystem implements Feeder {
    
    Ultrasonic feederUltrasonic;
    UltrasonicPoller poller;
    SmartDashboardTuner distanceTuner;

    public FeederImpl(){
        super(1, TimeUnit.SECONDS);
        
        this.feederUltrasonic = new Ultrasonic(RobotMap.ULTRASONIC_PORTS[0], RobotMap.ULTRASONIC_PORTS[1]);
        

        this.poller = new UltrasonicPoller(feederUltrasonic, 3000, 300);
        this.distanceTuner = new SmartDashboardTuner("Ball Distance", 300, d -> this.poller.setDistance(d));
        
    }

    public void task() {
        // PLACEHOLDER
        return;
    }

    @Override
    public void defineResources() {
        require(this.feederUltrasonic);
    }

    @Override
    public void prime() {
        // TODO Auto-generated method stub
    }

    @Override
    public void unload() {
        // TODO Auto-generated method stub

    }

    @Override
    public void enable(FeederComponent component) {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop(FeederComponent component) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enable(FeederComponent component, FeederDirection direction) {
        // TODO Auto-generated method stub

    }
}