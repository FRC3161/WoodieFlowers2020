package frc.robot;

import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechButton;
import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechControl;
import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechAxis;

public final class ControllerBindings {

        // Some of these seem kind of redundant, but it's best to consolidate everything here

        public static final LogitechButton INTAKE = LogitechButton.LEFT_TRIGGER;
        
        public static final LogitechControl RIGHT_STICk = LogitechControl.RIGHT_STICK;
        public static final LogitechControl LEFT_STICK = LogitechControl.LEFT_STICK;

        public static final LogitechAxis Y_AXIS = LogitechAxis.Y;
        public static final LogitechAxis X_AXIS = LogitechAxis.X;
}