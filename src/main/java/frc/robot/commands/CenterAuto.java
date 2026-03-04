// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANFuelSubsystem;
import frc.robot.subsystems.ClimberSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CenterAuto extends SequentialCommandGroup {
  /** Creates a new ExampleAuto. */
  public CenterAuto(CANDriveSubsystem driveSubsystem, CANFuelSubsystem ballSubsystem, ClimberSubsystem climbSubsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    // Lower the hooks so they are in frame perimeter
    new ClimbDown(climbSubsystem).withTimeout(3),
    // Drive backwards for 3 seconds. The driveArcadeAuto command factory
    // intentionally creates a command which does not end which allows us to control
    // the timing using the withTimeout decorator
    new AutoDrive(driveSubsystem, .2, 0).withTimeout(1),
    new AutoDrive(driveSubsystem,0.3,  0.0).withTimeout(6),
    new AutoDrive(driveSubsystem, 0.2, 0).withTimeout(2),
    // Spin up the launcher for 1 second and then launch balls for 9 seconds, for a
    // total of 10 seconds
    new LaunchSequence(ballSubsystem).withTimeout(5),
    new ClimbUp(climbSubsystem).withTimeout(4)
    );
  }
}
