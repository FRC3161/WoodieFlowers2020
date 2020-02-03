package frc.robot.subsystems.ball.intake;

import edu.wpi.first.wpilibj.Solenoid;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class IntakeImpl implements Intake {

    Solenoid intakeSolenoid;
    WPI_TalonSRX intakeMotorController;

    public IntakeImpl() {
        this.intakeSolenoid = new Solenoid(frc.robot.RobotMap.INTAKE_SOLENOID_CHANNEL);
        this.intakeMotorController = new WPI_TalonSRX(frc.robot.RobotMap.INTAKE_TALON_PORT);
    }
    
    public void extend(boolean motorDirection) {
        // Placeholder
        return;
    }

    public void extend() {
        extend(true);
    }

    public void retract() {
        // Placeholder
        return;
    }
}