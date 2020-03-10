package frc.robot.subsystems.ball.feeder;

import ca.team3161.lib.robot.subsystem.Subsystem;

public interface Feeder extends Subsystem{

    public enum FeederComponent {
        CONVEYOR,
        HOPPER
    }

    void prime();
    void unload();
    void enable(FeederComponent component);
    void stop(FeederComponent component);
}