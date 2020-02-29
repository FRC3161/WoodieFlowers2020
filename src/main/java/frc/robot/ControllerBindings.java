package frc.robot;

import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechButton;
import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechControl;
import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechAxis;
import ca.team3161.lib.utils.controls.LogitechDualAction.DpadDirection;

public final class ControllerBindings {
        public static final LogitechButton INTAKE = LogitechButton.RIGHT_BUMPER;
        public static final LogitechButton SHOOTER = LogitechButton.RIGHT_TRIGGER;
        public static final LogitechButton DEFLECTOR = LogitechButton.A;
        public static final DpadDirection FEEDER_UP = DpadDirection.UP;
        public static final DpadDirection FEEDER_DOWN = DpadDirection.DOWN;
        
        public static final LogitechButton DEPLOY_CLIMBER = LogitechButton.Y;
        public static final LogitechButton RUN_WINCH = LogitechButton.A;
        public static final DpadDirection STOP_WINCH = DpadDirection.LEFT;
        public static final LogitechButton REVERSE_INTAKE_DRIVER = LogitechButton.RIGHT_BUMPER;

        public static final LogitechControl RIGHT_STICK = LogitechControl.RIGHT_STICK;
        public static final LogitechControl LEFT_STICK = LogitechControl.LEFT_STICK;

        public static final LogitechAxis Y_AXIS = LogitechAxis.Y;
        public static final LogitechAxis X_AXIS = LogitechAxis.X;
}