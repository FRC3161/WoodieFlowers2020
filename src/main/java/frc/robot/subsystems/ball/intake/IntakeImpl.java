package frc.robot.subsystems.ball.intake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import java.util.EnumMap;
import java.util.concurrent.TimeUnit;

public class IntakeImpl extends RepeatingPooledSubsystem implements Intake {

    public enum MotorDirections {
        FORWARDS,
        BACKWARDS
    }

    EnumMap<MotorDirections, Integer> motorDirectionsMap;


    DoubleSolenoid intakeSolenoid;
    WPI_TalonSRX intakeMotorController;
    volatile boolean extended;
    int intakeDirection;
    double intakeSpeed;

    public IntakeImpl() {
        super(20, TimeUnit.MILLISECONDS);
        this.intakeSolenoid = new DoubleSolenoid(frc.robot.RobotMap.INTAKE_SOLENOID_IN_CHANNEL, frc.robot.RobotMap.INTAKE_SOLENOID_OUT_CHANNEL);
        this.intakeMotorController = new WPI_TalonSRX(frc.robot.RobotMap.INTAKE_TALON_PORT);
        if(this.intakeSolenoid.get() == DoubleSolenoid.Value.kForward){
            this.extended = true;
        } else {
            this.extended = false;
        }
        this.intakeSpeed = 0.8d;

        this.motorDirectionsMap = new EnumMap<>(MotorDirections.class);
        this.motorDirectionsMap.put(MotorDirections.FORWARDS, Integer.valueOf(1));
        this.motorDirectionsMap.put(MotorDirections.BACKWARDS, Integer.valueOf(-1));
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

    public void task(){
        if(this.extended) {
            this.intakeSolenoid.set(DoubleSolenoid.Value.kForward);
            this.intakeMotorController.set(this.intakeDirection * this.intakeSpeed);
        } else {
            this.intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
            this.intakeMotorController.set(0.0d);
        }
    }
}
