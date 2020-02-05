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
    double intakeSpeed;

    public IntakeImpl() {
        super(250, TimeUnit.MILLISECONDS); // Figure out actual value
        this.intakeSolenoid = new Solenoid(frc.robot.RobotMap.INTAKE_SOLENOID_CHANNEL);
        this.intakeMotorController = new WPI_TalonSRX(frc.robot.RobotMap.INTAKE_TALON_PORT);
        this.extended = this.intakeSolenoid.get();
        this.intakeSpeed = 0.8d;
    }
    
    public void extend(int motorDirection) {
        this.intakeDirection = motorDirection;
        this.extended = true;
    }

    public void extend() {
        extend(1);
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
            this.intakeSolenoid.set(true);
            this.intakeMotorController.set(this.intakeDirection * this.intakeSpeed);
        } else {
            this.intakeSolenoid.set(false);
            this.intakeMotorController.set(0.0d);
        }
    }
}
