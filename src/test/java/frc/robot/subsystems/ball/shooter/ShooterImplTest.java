package frc.robot.subsystems.ball.shooter;

import frc.robot.subsystems.ball.shooter.ShooterImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import edu.wpi.first.wpilibj.SpeedController;

public class ShooterImplTest {
    
    @Test
    public void testStopShooter(){
        SpeedController shooterMotorController = mock(SpeedController.class);

        ShooterImpl shooterSubsystem = new ShooterImpl(shooterMotorController);

        shooterSubsystem.stopShooter();

        assertEquals(Double.valueOf(0.0d), Double.valueOf(shooterMotorController.get()));
    }

}