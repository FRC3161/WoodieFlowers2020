package frc.robot;

import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechButton;
import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechControl;
public class ControllerBindings {

    public enum Bindings {
        INTAKE(LogitechButton.LEFT_TRIGGER),
        LEFT_STICK(LogitechControl.LEFT_STICK),
        RIGHT_STICK(LogitechControl.RIGHT_STICK);

        private final LogitechButton button;
        private final LogitechControl axis;
        Bindings(LogitechButton b) {
            this.button = b;
            this.axis = null;
        }

        Bindings(LogitechControl a){
            this.axis = a;
            this.button = null;
        }
        public LogitechButton getButton() {
            return this.button;
        }
        
        public LogitechControl getAxis() {
            return this.axis;
        }
    }

    ControllerBindings(){
    }

}