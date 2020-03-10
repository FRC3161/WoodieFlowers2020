package frc.robot.subsystems.ball.feeder;

import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Ultrasonic;
import frc.robot.subsystems.ball.feeder.UltrasonicPoller;
import ca.team3161.lib.utils.SmartDashboardTuner;
import java.util.concurrent.TimeUnit;
import frc.robot.RobotMap;

public class FeederImpl extends RepeatingPooledSubsystem implements Feeder {
    
    WPI_TalonSRX beltController;
    WPI_TalonSRX hopperController;

    Ultrasonic feederUltrasonic;
    UltrasonicPoller poller;
    SmartDashboardTuner distanceTuner;

    public FeederImpl(){
        super(1, TimeUnit.SECONDS);
        
        this.beltController = new WPI_TalonSRX(RobotMap.BELT_TALON_PORT);
        this.hopperController = new WPI_TalonSRX(RobotMap.HOPPER_TALON_PORT);
        this.feederUltrasonic = new Ultrasonic(RobotMap.ULTRASONIC_PORTS[0], RobotMap.ULTRASONIC_PORTS[1]);
        

        this.poller = new UltrasonicPoller(feederUltrasonic, 3000, 300);
        this.distanceTuner = new SmartDashboardTuner("Ball Distance", 300, d -> this.poller.setDistance(d));
        
        this.hopperController.configContinuousCurrentLimit(20, 250);
        this.hopperController.enableCurrentLimit(false);
    }

    public void task() {
        // PLACEHOLDER
        return;
    }

    @Override
    public void defineResources() {
        // TODO Auto-generated method stub

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