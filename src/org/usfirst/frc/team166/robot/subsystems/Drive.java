package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team166.robot.Robot;
import org.usfirst.frc.team166.robot.RobotMap;

/**
 *
 */
public class Drive extends Subsystem {

	Victor motorFrontRight = new Victor(RobotMap.frontRightMotor);
	Victor motorFrontLeft = new Victor(RobotMap.frontRightMotor);
	Victor motorRearRight = new Victor(RobotMap.frontRightMotor);
	Victor motorRearLeft = new Victor(RobotMap.frontRightMotor);

	Encoder encoderRight = new Encoder(RobotMap.rightEncoderA, RobotMap.rightEncoderB);
	Encoder encoderLeft = new Encoder(RobotMap.leftEncoderA, RobotMap.leftEncoderB);

	public void setMotorPower(double motorFrontRightPower, double motorFrontLeftPower, double motorRearRightPower,
			double motorRearLeftPower) {
		motorFrontRight.set(motorFrontRightPower);
		motorFrontLeft.set(motorFrontLeftPower);
		motorRearRight.set(motorRearRightPower);
		motorRearLeft.set(motorRearLeftPower);
	}

	public void stopMotors() {
		motorFrontRight.set(0);
		motorFrontLeft.set(0);
		motorRearRight.set(0);
		motorRearLeft.set(0);
	}

	public void driveJoysticks(double leftJoyVal, double rightJoyVal) {
		if (leftJoyVal < 0.1 && rightJoyVal <= 0.1) {
			Robot.drive.stopMotors();
		} else {
			Robot.drive.setMotorPower(leftJoyVal, rightJoyVal, leftJoyVal, rightJoyVal);
		}
	}

	public double getMotorSpeed(Encoder enc) {
		encoderRight.setDistancePerPulse((Math.PI * 4) / 1024); // Diameter of wheels is 4"
		encoderLeft.setDistancePerPulse((Math.PI * 4) / 1024);
		return enc.getRate();
	}

	public void resetEncoders() {
		encoderRight.reset();
		encoderLeft.reset();
	}

	public boolean areJoysticksInDeadzone() {
		double joyL = Robot.oi.getLeftY();
		double joyR = Robot.oi.getRightY();
		return ((Math.abs(joyL) - Math.abs(joyR)) < 0.1);
	}

	// public void setEncoderDistance() {
	//
	// }

	@Override
	public void initDefaultCommand() {
	}
}
