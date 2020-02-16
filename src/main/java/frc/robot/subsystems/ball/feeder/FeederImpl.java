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
    WPI_TalonSRX rollerController;

    Ultrasonic feederUltrasonic;
    UltrasonicPoller poller;
    SmartDashboardTuner distanceTuner;

    public FeederImpl(){
        super(1, TimeUnit.SECONDS);
        
        this.beltController = new WPI_TalonSRX(RobotMap.BELT_TALON_PORT);
        this.rollerController = new WPI_TalonSRX(RobotMap.ROLLER_TALON_PORT);
        this.feederUltrasonic = new Ultrasonic(RobotMap.ULTRASONIC_PORTS[0], RobotMap.ULTRASONIC_PORTS[1]);
        

        this.poller = new UltrasonicPoller(feederUltrasonic, 3000, 300);
        this.distanceTuner = new SmartDashboardTuner("Ball Distance", 300, d -> this.poller.setDistance(d));
    }

    public void feedBalls() {
        this.beltController.set(0.6d);
        this.rollerController.set(0.8d);
        // TODO Find actual values
    }

    public void stop() {
        this.beltController.set(0.0d);
        this.rollerController.set(0.0d);
    }

    void reverseFeeder() {
        this.beltController.set(-0.6d);
        this.rollerController.set(-0.8d);
        // TODO see above
    }

    public void defineResources(){
        require(beltController);
        require(rollerController);
    }

    public void unload() throws InterruptedException {
        while(!this.poller.checkUnloaded()){
            this.reverseFeeder();
            Thread.sleep(20);
        }
        this.stop();
    }

    public void task() {
        // PLACEHOLDER
        return;
    }
}