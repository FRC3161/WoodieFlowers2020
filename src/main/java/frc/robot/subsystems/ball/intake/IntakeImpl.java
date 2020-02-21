package frc.robot.subsystems.ball.intake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.RobotMap;
import ca.team3161.lib.robot.LifecycleEvent;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import java.util.EnumMap;
import java.util.concurrent.TimeUnit;

public class IntakeImpl extends RepeatingPooledSubsystem implements Intake {

    public enum MotorDirections {
        FORWARDS, BACKWARDS
    }

    EnumMap<MotorDirections, Integer> motorDirectionsMap;

    DoubleSolenoid intakeSolenoid;
    SpeedController intakeMotorController;
    volatile boolean extended;
    int intakeDirection;
    double intakeSpeed;

    public IntakeImpl(DoubleSolenoid sol, SpeedController mc) {
        super(20, TimeUnit.MILLISECONDS);
        this.intakeSolenoid = sol;
        this.intakeMotorController = mc;
        if (this.intakeSolenoid.get() == DoubleSolenoid.Value.kForward) {
            this.extended = true;
        } else {
            this.extended = false;
        }
        this.intakeSpeed = 0.8d;

        this.motorDirectionsMap = new EnumMap<>(MotorDirections.class);
        this.motorDirectionsMap.put(MotorDirections.FORWARDS, Integer.valueOf(1));
        this.motorDirectionsMap.put(MotorDirections.BACKWARDS, Integer.valueOf(-1));
    }

    public IntakeImpl() {
        this(new DoubleSolenoid(RobotMap.INTAKE_SOLENOID_IN_CHANNEL, RobotMap.INTAKE_SOLENOID_OUT_CHANNEL),
                new WPI_TalonSRX(RobotMap.INTAKE_TALON_PORT));
    }

    public void extend(MotorDirections direction) {
        this.intakeDirection = this.motorDirectionsMap.get(direction);
        this.extended = true;
    }

    public void extend() {
        extend(MotorDirections.FORWARDS);
    }

    public void retract() {
        this.extended = false;
    }

    public void defineResources() {
        require(intakeMotorController);
        require(intakeSolenoid);
    }

    public boolean getDeployed() {
        return this.extended;
    }

    @Override

    public void task() {
        if (this.extended) {
            this.intakeSolenoid.set(DoubleSolenoid.Value.kForward);
            this.intakeMotorController.set(this.intakeDirection * this.intakeSpeed);
        } else {
            this.intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
            this.intakeMotorController.set(0.0d);
        }
    }

    @Override
    public void lifecycleStatusChanged(LifecycleEvent previous, LifecycleEvent current) {
        if(current.equals(LifecycleEvent.ON_AUTO)) {
            start();
        } else if(current.equals(LifecycleEvent.ON_DISABLED)) {
            cancel();
        }
    }
}
