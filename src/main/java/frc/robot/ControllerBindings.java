package frc.robot;

import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechAxis;
import ca.team3161.lib.utils.controls.LogitechDualAction.LogitechControl;
import ca.team3161.lib.utils.controls.LogitechDualAction;
import ca.team3161.lib.utils.controls.SquaredJoystickMode;

public class ControllerBindings {

    private LogitechDualAction driverPad;

    public static final LogitechControl DRIVE_LEFT_STICK = LogitechControl.LEFT_STICK;
    public static final LogitechControl DRIVE_RIGHT_STICK = LogitechControl.RIGHT_STICK;

    ControllerBindings(int driverPort){
        this.driverPad = new LogitechDualAction(driverPort);
        this.driverPad.setMode(DRIVE_LEFT_STICK, LogitechAxis.Y, new SquaredJoystickMode());
        this.driverPad.setMode(DRIVE_RIGHT_STICK, LogitechAxis.Y, new SquaredJoystickMode());
    }

    public double driverLeftStickY(){
        return this.driverPad.getValue(DRIVE_LEFT_STICK, LogitechAxis.Y);
    }

    public double driverRightStickY(){
        return this.driverPad.getValue(DRIVE_RIGHT_STICK, LogitechAxis.Y);
    }

}