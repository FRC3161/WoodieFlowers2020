package frc.robot.subsystems.ball.feeder.hopper;

import java.util.concurrent.TimeUnit;

import ca.team3161.lib.robot.LifecycleEvent;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.RobotMap;

public class HopperImpl extends RepeatingPooledSubsystem implements Hopper{

    enum HopperState {
        FEEDING,
        OFF,
        UNLOADING
    }

    WPI_TalonSRX hopperController;
    HopperState state;
    double hopperSpeed;


    HopperImpl() {
        super(1, TimeUnit.SECONDS);

        this.hopperController = new WPI_TalonSRX(RobotMap.HOPPER_TALON_PORT);
        this.state = HopperState.OFF;
        this.hopperSpeed = 0.8d;
    }

    @Override
    public void task() {
        if(this.state == HopperState.FEEDING) {
            this.hopperController.set(hopperSpeed);
        } else if(this.state == HopperState.UNLOADING) {
            this.hopperController.set(-hopperSpeed);
        } else {
            this.hopperController.set(0.0d);
        }
    }

    @Override
    public void defineResources() {
        require(this.hopperController);
    }

    @Override
    public void lifecycleStatusChanged(LifecycleEvent previous, LifecycleEvent current) {
        switch(current){
            case ON_TELEOP:
            case ON_AUTO:
            case ON_TEST:
                this.start();
                break;
            default:
                this.cancel();
        }
    }

    @Override
    public void feed() {
        this.state = HopperState.FEEDING;
    }

    @Override
    public void unload() {
        this.state = HopperState.UNLOADING;
    }

    @Override
    public void stop() {
        this.state = HopperState.OFF;
    }

}