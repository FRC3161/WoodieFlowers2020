package frc.robot.subsystems.ball.intake;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.junit.Before;
import frc.robot.subsystems.ball.intake.IntakeImpl;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;

public class IntakeImplTest {
    SpeedController intakeMotorController;
    DoubleSolenoid intakeSolenoid;
    IntakeImpl intakeSubsystem;

    @Before
    public void setup() {
        intakeMotorController = mock(SpeedController.class);
        intakeSolenoid = mock(DoubleSolenoid.class);
        intakeSubsystem = new IntakeImpl(intakeSolenoid, intakeMotorController);
    }

    @Test
    public void testConstructor(){
        // Constructor is run earlier

        Mockito.when(intakeSolenoid.get()).thenReturn(DoubleSolenoid.Value.kReverse);

        // Check the initial state of the solenoid
        assertEquals(false, intakeSubsystem.getDeployed());
    }

    @Test
    public void testExtend() {
        // Extend the intake
        intakeSubsystem.extend();

        // Check if it behaves as expected
        assertEquals(true, intakeSubsystem.getDeployed());
    }

    @Test
    public void testRetract() {
        // Retract the intake
        intakeSubsystem.retract();

        // Check behaviour
        assertEquals(false, intakeSubsystem.getDeployed());
    }
}