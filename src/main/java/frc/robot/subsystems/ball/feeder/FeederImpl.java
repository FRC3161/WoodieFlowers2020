package frc.robot.subsystems.ball.feeder;

import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import java.util.concurrent.TimeUnit;

public class FeederImpl extends RepeatingPooledSubsystem implements Feeder {
    
    WPI_TalonSRX beltController;
    WPI_TalonSRX rollerController;

    public FeederImpl(){
        super(1, TimeUnit.SECONDS);
        
        this.beltController = new WPI_TalonSRX(frc.robot.RobotMap.BELT_TALON_PORT);
        this.rollerController = new WPI_TalonSRX(frc.robot.RobotMap.ROLLER_TALON_PORT);
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

    public void unload() {
        this.reverseFeeder();
        // TODO implement sensor logic
    }

    public void task() {
        // PLACEHOLDER
        return;
    }
}