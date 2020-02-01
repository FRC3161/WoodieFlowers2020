package frc.robot;

import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechAxis;
import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechControl;
import ca.team3161.lib.utils.controls.LogitechDualAction;
import ca.team3161.lib.utils.controls.SquaredJoystickMode;

public class ControllerBindings {

    private LogitechDualAction driverPad;
    private LogitechDualAction operatorPad;
    private static final int DRIVER_PORT = 0;
    private static final int OPERATOR_PORT = 1;

    public static final LogitechControl DRIVE_LEFT_STICK = LogitechControl.LEFT_STICK;
    public static final LogitechControl DRIVE_RIGHT_STICK = LogitechControl.RIGHT_STICK;

    ControllerBindings(){
        // Driver Pad
        this.driverPad = new LogitechDualAction(DRIVER_PORT);

        this.driverPad.setMode(DRIVE_LEFT_STICK, LogitechAxis.Y, new SquaredJoystickMode());
        this.driverPad.setMode(DRIVE_RIGHT_STICK, LogitechAxis.Y, new SquaredJoystickMode());
        
        // Operator Pad
        this.operatorPad = new LogitechDualAction(OPERATOR_PORT);
    }

    public double driverLeftStickY(){
        return this.driverPad.getValue(DRIVE_LEFT_STICK, LogitechAxis.Y);
    }

    public double driverRightStickY(){
        return this.driverPad.getValue(DRIVE_RIGHT_STICK, LogitechAxis.Y);
    }

}