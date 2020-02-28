/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import ca.team3161.lib.robot.TitanBot;
import ca.team3161.lib.utils.controls.LogitechDualAction;
import ca.team3161.lib.utils.controls.Gamepad.PressType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.DrivetrainImpl;
import frc.robot.subsystems.ball.BallImpl;
import frc.robot.subsystems.ball.Ball;
import frc.robot.subsystems.climb.Climber;
import frc.robot.subsystems.climb.ClimberImpl;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.ControllerBindings;
import frc.robot.Autonomous;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TitanBot {
  private Drivetrain drive;
  
  private LogitechDualAction driverPad; 
  private LogitechDualAction operatorPad;
  private Ball ballSubsystem;
  private Autonomous auto;
  private Climber climb;
  private Compressor comp;

  private static final String kWallAuto = "Wall Auto";
  private static final String kTrenchAuto = "Trench Auto";
  private String selectedAuto;
  private final SendableChooser<String> auto_chooser = new SendableChooser<>();

  @Override
  public int getAutonomousPeriodLengthSeconds() {
    return 15;
  }

  @Override
  public void disabledSetup(){
    this.comp.start();
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
    this.drive = new DrivetrainImpl();
    this.driverPad = new LogitechDualAction(RobotMap.DRIVER_PAD_PORT); // TODO idk if this is the right port
    this.operatorPad = new LogitechDualAction(RobotMap.OPERATOR_PAD_PORT);
    this.ballSubsystem = new BallImpl();
    this.climb = new ClimberImpl();
    this.auto = new Autonomous(this.drive, this.ballSubsystem);
    this.comp = new Compressor(0);

    auto_chooser.setDefaultOption("Wall Auto", kWallAuto);
    auto_chooser.addOption("Trench Auto", kTrenchAuto);
    SmartDashboard.putData("Auto Chooser", auto_chooser);

    registerLifecycleComponent(driverPad);
    registerLifecycleComponent(operatorPad);
    registerLifecycleComponent(ballSubsystem);
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
    selectedAuto = auto_chooser.getSelected();
  }

  /**
   * This function is called periodically during autonomous.
   * 
   * @throws InterruptedException
   */
  @Override
  public void autonomousRoutine() throws InterruptedException {
    switch(selectedAuto){
      case kWallAuto:
        this.auto.wallShothitNRun();
        break;
      case kTrenchAuto:
        this.auto.hitNRun();
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopSetup() {
    
    this.climb.retractClimber();

    this.driverPad.bind(ControllerBindings.REVERSE_INTAKE_DRIVER, PressType.PRESS, () -> ballSubsystem.collect(false));
    this.driverPad.bind(ControllerBindings.REVERSE_INTAKE_DRIVER, PressType.PRESS, () -> ballSubsystem.retract());
    this.operatorPad.bind(ControllerBindings.INTAKE, PressType.PRESS, () -> ballSubsystem.collect());
    this.operatorPad.bind(ControllerBindings.INTAKE, PressType.RELEASE, () -> ballSubsystem.retract());
    this.operatorPad.bind(ControllerBindings.SHOOTER, PressType.PRESS, () -> ballSubsystem.shoot());
    this.operatorPad.bind(ControllerBindings.SHOOTER, PressType.RELEASE, () -> ballSubsystem.stop()); 
  }

  @Override
  public void teleopRoutine() {
    //TODO properly bind controls
    //this.drive.driveTank(this.driverPad.getValue(ControllerBindings.LEFT_STICK, ControllerBindings.Y_AXIS), this.driverPad.getValue(ControllerBindings.RIGHT_STICK, ControllerBindings.Y_AXIS)); // Yeah it's shorter the old way, but this way we keep all of the bindings in one place
    this.drive.driveArcade(this.driverPad.getValue(ControllerBindings.LEFT_STICK, ControllerBindings.Y_AXIS), this.driverPad.getValue(ControllerBindings.RIGHT_STICK, ControllerBindings.X_AXIS));

    // TODO talk with driveteam about controls
    this.drive.driveArcade(this.driverPad.getValue(ControllerBindings.LEFT_STICK, ControllerBindings.Y_AXIS), this.driverPad.getValue(ControllerBindings.RIGHT_STICK, ControllerBindings.X_AXIS));
    if(this.operatorPad.getDpadDirection().equals(ControllerBindings.FEEDER_UP)) {
      this.ballSubsystem.feedBalls();
    } else if(this.operatorPad.getDpadDirection().equals(ControllerBindings.FEEDER_DOWN)) {
      this.ballSubsystem.unfeedBalls();
    } else {
      this.ballSubsystem.stopFeeder();
    }

    if(this.driverPad.getDpadDirection().equals(ControllerBindings.LIFT)) {
      this.climb.liftRobot();
      this.comp.stop();
    } else if(this.driverPad.getDpadDirection().equals(ControllerBindings.DEPLOY_CLIMBER)) {
      this.climb.extendClimber();
    } else if(this.driverPad.getDpadDirection().equals(ControllerBindings.RUN_WINCH)) {
      this.climb.liftRobot();
    } else {
      this.climb.stopClimber();
    }
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
