package frc.robot;

import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechButton;

public class ControllerBindings {

    public enum Bindings {
        INTAKE(LogitechButton.LEFT_TRIGGER);

        private final LogitechButton button;
        Bindings(LogitechButton b) {
            this.button = b;
        }
        public LogitechButton getButton() {
            return this.button;
        }
    }

    ControllerBindings(){
    }

}