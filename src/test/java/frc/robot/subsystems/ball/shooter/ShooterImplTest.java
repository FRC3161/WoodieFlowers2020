package frc.robot.subsystems.ball.shooter;

import frc.robot.subsystems.ball.shooter.ShooterImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import edu.wpi.first.wpilibj.SpeedController;

public class ShooterImplTest {
    
    @Test
    public void testStopShooter(){
        // Create a mock speedcontroller
        SpeedController shooterMotorController = mock(SpeedController.class);

        // Initialize the shooter subsystem with the mock speedcontroller
        ShooterImpl shooterSubsystem = new ShooterImpl(shooterMotorController);

        // Method from the shooter subsystem
        shooterSubsystem.stopShooter();

        // Testing that the method did what we actually wanted
        assertEquals(Double.valueOf(0.0d), Double.valueOf(shooterMotorController.get()));
    }

}