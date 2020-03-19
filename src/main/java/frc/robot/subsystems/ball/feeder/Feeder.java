package frc.robot.subsystems.ball.feeder;

import ca.team3161.lib.robot.subsystem.Subsystem;

public interface Feeder extends Subsystem{

    public enum FeederComponent {
        CONVEYOR,
        HOPPER
    }

    public enum FeederDirection {
        FORWARDS,
        REVERSE
    }

    void prime();
    void unload();
    void feed();
    void disable();
    void setShooting(boolean shooting);
}