package frc.robot;

import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechButton;
import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechControl;
import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechAxis;

public class ControllerBindings {

    public enum Bindings {
        INTAKE(LogitechButton.LEFT_TRIGGER),
        LEFT_STICK(LogitechControl.LEFT_STICK),
        RIGHT_STICK(LogitechControl.RIGHT_STICK),
        Y_AXIS(LogitechAxis.Y),
        X_AXIS(LogitechAxis.X);

        private final LogitechButton button;
        private final LogitechControl stick;
        private final LogitechAxis axis;

        Bindings(LogitechButton b) {
            this.button = b;
            this.stick = null;
            this.axis = null;
        }

        Bindings(LogitechControl a){
            this.stick = a;
            this.button = null;
            this.axis = null;
        }

        Bindings(LogitechAxis ax) {
            this.axis = ax;
            this.button = null;
            this.stick = null;
        }

        public LogitechButton getButton() {
            return this.button;
        }
        
        public LogitechControl getStick() {
            return this.stick;
        }

        public LogitechAxis getAxis() {
            return this.axis;
        } 
    }

    ControllerBindings(){
    }

}