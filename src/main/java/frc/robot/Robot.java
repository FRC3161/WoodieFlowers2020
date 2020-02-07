/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import ca.team3161.lib.robot.TitanBot;
import ca.team3161.lib.utils.controls.LogitechDualAction;
import ca.team3161.lib.utils.controls.Gamepad.PressType;

import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.DrivetrainImpl;
import frc.robot.subsystems.ball.BallImpl;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TitanBot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private Drivetrain drive;
  
  private LogitechDualAction driverPad; 
  private LogitechDualAction operatorPad;
  private ControllerBindings controls;
  private BallImpl ballSubsystem;

  @Override
  public int getAutonomousPeriodLengthSeconds() {
    return 15;
  }

  @Override
  public void disabledSetup(){
  }

  @Override
  public void disabledRoutine() {
  }


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotSetup() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    this.drive = new DrivetrainImpl();
    this.driverPad = new LogitechDualAction(RobotMap.DRIVER_PAD_PORT); // TODO idk if this is the right port
    this.operatorPad = new LogitechDualAction(RobotMap.OPERATOR_PAD_PORT);
    this.ballSubsystem = new BallImpl();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousSetup() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousRoutine() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopSetup() {
    //TODO make the controller actually drive the robot
    this.operatorPad.bind(ControllerBindings.Bindings.INTAKE.getButton(), PressType.PRESS, () -> ballSubsystem.collect());
    this.operatorPad.bind(ControllerBindings.Bindings.INTAKE.getButton(), PressType.RELEASE, () -> ballSubsystem.retract());
  }

  @Override
  public void teleopRoutine() {
    //TODO properly bind controls
    this.drive.drive(controls.driverLeftStickY(), controls.driverRightStickY());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testSetup() {
  }

  @Override
  public void testRoutine(){

  }
}
