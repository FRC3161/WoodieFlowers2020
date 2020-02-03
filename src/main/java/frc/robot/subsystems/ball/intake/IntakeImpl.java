package frc.robot.subsystems.ball.intake;

import edu.wpi.first.wpilibj.Solenoid;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import ca.team3161.lib.robot.subsystem.RepeatingPooledSubsystem;
import java.util.concurrent.TimeUnit;

public class IntakeImpl extends RepeatingPooledSubsystem implements Intake {

    Solenoid intakeSolenoid;
    WPI_TalonSRX intakeMotorController;
    boolean extended;
    int intakeDirection;

    public IntakeImpl() {
        super(250, TimeUnit.MILLISECONDS); // Figure out actual value
        this.intakeSolenoid = new Solenoid(frc.robot.RobotMap.INTAKE_SOLENOID_CHANNEL);
        this.intakeMotorController = new WPI_TalonSRX(frc.robot.RobotMap.INTAKE_TALON_PORT);
        this.extended = this.intakeSolenoid.get();
    }
    
    public void extend(int motorDirection) {
        this.intakeDirection = motorDirection;
        this.extended = true;
    }

    public void extend() {
        extend(1);
        this.extended = true;
    }

    public void retract() {
        this.extended = false;
    }

    public void defineResources() {
        require(intakeMotorController);
        require(intakeSolenoid);
    }

    public void task(){
        // Placeholder
        return;
    }
}
