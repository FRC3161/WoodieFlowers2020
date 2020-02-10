package frc.robot.subsystems.ball.intake;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import org.junit.runner.RunWith;

import frc.robot.subsystems.ball.intake.IntakeImpl;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;

import frc.robot.SingleInstanceRunner;

@RunWith(SingleInstanceRunner.class)
public class IntakeImplTest {
    static SpeedController intakeMotorController = mock(SpeedController.class);
    static DoubleSolenoid intakeSolenoid = mock(DoubleSolenoid.class);
    static IntakeImpl intakeSubsystem = mock(IntakeImpl.class);

    @Test
    public void testConstructor(){
        // Constructor is run earlier
        // Check the initial state of the solenoid
        assertEquals(false, intakeSubsystem.getDeployed());
    }

    @Test
    public void testExtend() {
        // Extend the intake
        intakeSubsystem.extend();

        // Check if it behaves as expected
        assertEquals(false, intakeSubsystem.getDeployed());
    }

    @Test
    public void testRetract() {
        // Retract the intake
        intakeSubsystem.retract();

        // Check behaviour
        assertEquals(false, intakeSubsystem.getDeployed());
    }
}