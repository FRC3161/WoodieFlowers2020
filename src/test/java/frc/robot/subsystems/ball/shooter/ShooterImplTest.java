package frc.robot.subsystems.ball.shooter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Encoder;

public class ShooterImplTest {
    SpeedController shooterMotorController;
    ShooterImpl shooterSubsystem;
    Encoder shooterEncoder;
   
    @Before
    public void setup() {
        shooterMotorController = mock(SpeedController.class);
        shooterEncoder = mock(Encoder.class);
        shooterSubsystem = new ShooterImpl(shooterMotorController, shooterEncoder);
    }

    @Test
    public void testStopShooter(){
        // Method from the shooter subsystem
        shooterSubsystem.stopShooter();

        // Specifying return value of mock method
        Mockito.when(shooterMotorController.get()).thenReturn(0.0d);

        // Testing that the method did what we actually wanted
        assertEquals(Double.valueOf(0.0d), Double.valueOf(shooterMotorController.get()));
    }
    @Test
    public void testReadyForBalls() {
        Mockito.when(shooterEncoder.getRate()).thenReturn(0.0);
        assertEquals(Boolean.valueOf(false), Boolean.valueOf(shooterSubsystem.readyForBalls()));
    }

    @Test
    public void testReadyForBallsTrue() {
        Mockito.when(shooterEncoder.getRate()).thenReturn(100000.0);
        assertEquals(Boolean.valueOf(true), Boolean.valueOf(shooterSubsystem.readyForBalls()));
    }
}