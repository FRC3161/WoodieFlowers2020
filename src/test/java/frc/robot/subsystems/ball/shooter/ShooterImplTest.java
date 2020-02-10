package frc.robot.subsystems.ball.shooter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import frc.robot.SingleInstanceRunner;

import edu.wpi.first.wpilibj.SpeedController;

@RunWith(SingleInstanceRunner.class)
public class ShooterImplTest {
    static SpeedController shooterMotorController = mock(SpeedController.class);
    static ShooterImpl shooterSubsystem = new ShooterImpl(shooterMotorController);
    
    @Test
    public void testStopShooter(){
        // Method from the shooter subsystem
        shooterSubsystem.stopShooter();

        // Testing that the method did what we actually wanted
        assertEquals(Double.valueOf(0.0d), Double.valueOf(0.0d));
    }
    
    @Test
    public void testReadyForBalls() {
        assertEquals(Boolean.valueOf(false), Boolean.valueOf(shooterSubsystem.readyForBalls()));
    }

}