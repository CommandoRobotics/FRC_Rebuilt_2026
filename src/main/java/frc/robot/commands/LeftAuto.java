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
public class LeftAuto extends SequentialCommandGroup {

public LeftAuto(CANDriveSubsystem driveSubsystem, CANFuelSubsystem ballSubsystem, ClimberSubsystem climbSubsystem) {
  // Do all the following commands
  addCommands(
    // Lower the hooks so they are in frame perimeter
    new ClimbDown(climbSubsystem).withTimeout(3),

    // Drive backwards to the shooting line
    new AutoDrive(driveSubsystem, .2, 0).withTimeout(1),
    new AutoDrive(driveSubsystem,0.3,  0.0).withTimeout(6),

    // Turn toward the Hub
    new AutoDrive(driveSubsystem, 0.0, 0.2).withTimeout(2),

    // Shoot
    new LaunchSequence(ballSubsystem).withTimeout(5),

    // Turn back toward the Tower
    new AutoDrive(driveSubsystem, 0.0, -0.2).withTimeout(2),

    // Drive to the tower
    new AutoDrive(driveSubsystem, .2, 0).withTimeout(1),
    new AutoDrive(driveSubsystem,0.3,  0.0).withTimeout(2),
    new AutoDrive(driveSubsystem, 0.2, 0).withTimeout(2),

    // Climb
    new ClimbUp(climbSubsystem).withTimeout(4)
  );


}
}
