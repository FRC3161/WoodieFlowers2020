package frc.robot.subsystems.ball.feeder;

import ca.team3161.lib.robot.subsystem.Subsystem;

public interface Feeder extends Subsystem{

    public enum FeederComponent {
        CONVEYOR,
        HOPPER
    }

    public enum FeederDirection {
        FORWARDS(1),
        REVERSE(-1);

        int direction;

        FeederDirection(int direction) {
            this.direction  = direction;
        }

        public int getDirection() {
            return this.direction;
        }
    }

    void prime();
    void unload();
    void enable(FeederComponent component);
    void enable(FeederComponent component, FeederDirection direction);
    void stop(FeederComponent component);
}