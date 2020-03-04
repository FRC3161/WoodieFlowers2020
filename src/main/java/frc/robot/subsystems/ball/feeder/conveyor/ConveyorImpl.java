package frc.robot.subsystems.ball.feeder.conveyor;

import java.util.concurrent.TimeUnit;
import ca.team3161.lib.robot.LifecycleEvent;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import edu.wpi.first.wpilibj.Ultrasonic;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.RobotMap;
import frc.robot.subsystems.ball.feeder.UltrasonicPoller;


public class ConveyorImpl extends RepeatingPooledSubsystem implements Conveyor {

    public enum ConveyorState {
        FEEDING,
        OFF,
        UNLOADING,
        PRIMING
    }

    WPI_TalonSRX conveyorController;
    volatile ConveyorState state;

    Ultrasonic topUltrasonic;
    double topUltrasonicDistanceMM;

    Ultrasonic bottomUltrasonic;
    UltrasonicPoller bottomUltrasonicPoller;

    ConveyorImpl() {
        super(1, TimeUnit.SECONDS);
        this.conveyorController = new WPI_TalonSRX(RobotMap.BELT_TALON_PORT);
        this.conveyorController.setInverted(true);
        this.state = ConveyorState.OFF;

        this.topUltrasonic = new Ultrasonic(RobotMap.TOP_ULTRASONIC_PORTS[0], RobotMap.TOP_ULTRASONIC_PORTS[1]);
        this.topUltrasonicDistanceMM = 30.0;

        this.bottomUltrasonic = new Ultrasonic(RobotMap.ULTRASONIC_PORTS[0], RobotMap.ULTRASONIC_PORTS[1]);
        this.bottomUltrasonicPoller = new UltrasonicPoller(this.bottomUltrasonic, 3000, 30); // TODO setup tuners
    }

    @Override
    public void defineResources() {
        require(this.conveyorController);
    }

    @Override
    public void task() {
        if(this.state == ConveyorState.FEEDING) {
            this.conveyorController.set(0.95d);
        } else if(this.state == ConveyorState.UNLOADING) {
            this.conveyorController.set(-0.95d);
            if(this.bottomUltrasonicPoller.checkUnloaded()) {
                this.state = ConveyorState.OFF;
            }
        } else if(this.state == ConveyorState.PRIMING){
            this.conveyorController.set(0.95d);
            if(this.topUltrasonic.getRangeMM() < this.topUltrasonicDistanceMM) {
                this.state = ConveyorState.OFF;
            }
        } else {
            this.conveyorController.set(0.0d);
        }
    }

    @Override
    public void lifecycleStatusChanged(LifecycleEvent previous, LifecycleEvent current) {
        switch(current) {
            case ON_AUTO:
            case ON_TELEOP:
            case ON_TEST:
                this.start();
                break;
            default:
                this.cancel();
        }
    }

    @Override
    public void feed() {
        this.state = ConveyorState.FEEDING;
    }

    @Override
    public void prime() {
        this.state = ConveyorState.PRIMING;
    }

    @Override
    public void unload() {
        this.state = ConveyorState.UNLOADING;
    }

    @Override
    public void stop() {
        this.state = ConveyorState.OFF;
    }

}