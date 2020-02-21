package frc.robot.subsystems.ball.shooter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class ShooterImplTest {
    WPI_TalonSRX shooterMotorController1;
    WPI_TalonSRX shooterMotorController2;
    DoubleSolenoid hatch;
    ShooterImpl shooterSubsystem;
    
    @Before
    public void setup() {
        shooterMotorController1 = mock(WPI_TalonSRX.class);
        shooterMotorController2 = mock(WPI_TalonSRX.class);
        hatch = mock(DoubleSolenoid.class);
        shooterSubsystem = new ShooterImpl(shooterMotorController1, shooterMotorController2, hatch);
    }

    @Test
    public void testStopShooter(){
        // Method from the shooter subsystem
        shooterSubsystem.stopShooter();
        shooterSubsystem.task();

        // Specifying return value of mock method
        Mockito.when(shooterMotorController1.get()).thenReturn(0.0d);
        Mockito.verify(shooterMotorController1).set(0.0d);

        Mockito.when(shooterMotorController2.get()).thenReturn(0.0d);
        Mockito.verify(shooterMotorController2).set(0.0d);

        // Testing that the method did what we actually wanted
        assertEquals(Double.valueOf(0.0d), Double.valueOf(shooterMotorController1.get()));
        assertEquals(Double.valueOf(0.0d), Double.valueOf(shooterMotorController2.get()));
    }

    @Test
    public void testReadyForBalls() {
        Mockito.when(shooterMotorController1.getSelectedSensorVelocity()).thenReturn(0);
        assertEquals(Boolean.valueOf(false), Boolean.valueOf(shooterSubsystem.readyForBalls()));
    }

    @Test
    public void testReadyForBallsTrue() {
        Mockito.when(shooterMotorController1.getSelectedSensorVelocity()).thenReturn(100000);
        assertEquals(Boolean.valueOf(true), Boolean.valueOf(shooterSubsystem.readyForBalls()));
    }
    
}