package org.firstinspires.ftc.teamcode.marvframework.utils;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;

import java.util.HashMap;
import java.util.List;

/**
 * Created by andy on 4/7/17.
 *
 * Static class containing random useful methods, especially for MarvAutonomous
 *
 * "Tick" and "count" are equivalent in code and JavaDoc
 *
 * All methods are thread-safe from each other, but the arguments given may not necessarily be thread-safe
 */

@SuppressWarnings({"StatementWithEmptyBody", "unused"})
public class MarvUtils {

    /**
     * How long to remain in a method loop before force leaving (in seconds)
     */
    public static final double timeout = 15.0; //Seconds

    /**
     * Turning for a set of motors, given an array of gyros and a turn angle
     * It is assumed that negative degrees is counter-clockwise (left).
     *
     * Encoders are not used in this method
     * Gyros are not reset in this method
     * Motors are reset to their original mode, with 0 power
     *
     * The errorThreshold is the amount of leeway allowed for the turn to complete.
     * If the current angle is within errorThreshold of degrees, then then turn is complete.
     *
     * This method will time out after timeout seconds has passed
     *
     * @param degrees        the number of degrees to turn (can be negative)
     * @param errorThreshold the amount of leeway (in degrees) to stop turning at
     * @param leftMotors     the list of left motors to use
     * @param rightMotors    the list of right motors to use
     * @param gyros          the list of gyros to use
     */
    // TODO make work
    public static void turn(double degrees, double errorThreshold, List<DcMotor> leftMotors, List<DcMotor> rightMotors, List<ModernRoboticsI2cGyro> gyros) {

        //Reject null
        if (leftMotors.size() == 0 || rightMotors.size() == 0 || gyros.size() == 0 || degrees == 0) return;

        //Save the current run modes of the motors and reset the motors
        HashMap<DcMotor, DcMotor.RunMode> motorModes = new HashMap<>();
        for (DcMotor motor : leftMotors) {
            motorModes.put(motor, motor.getMode());
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        for (DcMotor motor : rightMotors) {
            motorModes.put(motor, motor.getMode());
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        double error; //Current error measurement
        boolean turnComplete = false; // Whether we are finished or not
        ElapsedTime elapsedTime = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        elapsedTime.reset();

        while (!turnComplete) {
            error = degrees - getGyroAverage(gyros);

            //Exits the turn if error is <= than threshold
            if (Math.abs(error) <= Math.abs(errorThreshold)) turnComplete = true;

            else {
                //As error gets smaller, lower speed

                if (error > 30) {
                    setPower(leftMotors, rightMotors, -.65, .65);
                } else if (error < -30) {
                    setPower(leftMotors, rightMotors, .65, -.65);

                } else if (error > 22) {
                    setPower(leftMotors, rightMotors, -.55, .55);
                } else if (error < -22) {
                    setPower(leftMotors, rightMotors, .55, -.55);

                } else if (error > 10) {
                    setPower(leftMotors, rightMotors, -.4, .4);
                } else if (error < -10) {
                    setPower(leftMotors, rightMotors, .4, -.4);

                } else if (error > 1) {
                    setPower(leftMotors, rightMotors, -.35, .35);
                } else if (error < -1) {
                    setPower(leftMotors, rightMotors, .35, -.35);

                } else {
                    setPower(leftMotors, rightMotors, 0, 0);
                }
            }

            //Screw it check
            if (elapsedTime.seconds() >= timeout) turnComplete = true;
        }

        //Reset motor modes
        for (DcMotor motor : leftMotors) motor.setMode(motorModes.get(motor));
        for (DcMotor motor : rightMotors) motor.setMode(motorModes.get(motor));
        motorModes.clear();
    }

    //For the turn method
    private static void setPower(List<DcMotor> leftMotors, List<DcMotor> rightMotors, double leftPower, double rightPower) {
        for (DcMotor motor : leftMotors) motor.setPower(leftPower);
        for (DcMotor motor : rightMotors) motor.setPower(rightPower);
    }
    private static double getGyroAverage(List<ModernRoboticsI2cGyro> gyros) {
        double sum = 0;
        for (ModernRoboticsI2cGyro gyro : gyros) sum += gyro.getIntegratedZValue();
        return sum / gyros.size();
    }

    /**
     * Drives forward at a given speed/power for a specified time (seconds).
     *
     * Encoders are not used in this method
     * Motors are reset to their original mode, with 0 power
     *
     * This method will time out after timeout seconds has passed
     *
     * @param seconds the number of seconds to drive forward
     * @param speed the speed / power to run the motors at (can be negative)
     * @param motors the list of motors to run
     */
    public static void driveForwardForTime(double seconds, double speed, DcMotor... motors) {

        if (seconds <= 0 || speed == 0 || motors.length == 0) return;

        //Save the current run modes of the motors and reset the motors
        HashMap<DcMotor, DcMotor.RunMode> motorModes = new HashMap<>();
        for (DcMotor motor : motors) {
            motorModes.put(motor, motor.getMode());
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        //Set up time
        double time = Math.min(seconds, timeout);
        ElapsedTime eTime = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        eTime.reset();

        //Run the motors
        for (DcMotor motor : motors) motor.setPower(speed);
        while (eTime.seconds() <= time) {} //Wait n chill

        //Reset
        for (DcMotor motor : motors) {
            motor.setPower(0);
            motor.setMode(motorModes.get(motor));
        }
        motorModes.clear();
    }

    /**
     * Drives forward at a given speed/power until a stop is requested.
     *
     * Encoders are not used in this method
     * Motors are reset to their original mode, with 0 power
     *
     * This method will time out after timeout seconds has passed
     *
     * @param whenToStop dictates when to stop (if true, then we stop)
     * @param speed the speed / power to run the motors at (can be negative)
     * @param motors the list of motors to run
     */
    public static void driveForwardUntil(Func<Boolean> whenToStop, double speed, DcMotor... motors) {

        if (motors.length == 0 || speed == 0 || whenToStop == null || whenToStop.value()) return;

        //Save the current run modes of the motors and reset the motors
        HashMap<DcMotor, DcMotor.RunMode> motorModes = new HashMap<>();
        for (DcMotor motor : motors) {
            motorModes.put(motor, motor.getMode());
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        //Run motors
        ElapsedTime eTime = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        eTime.reset();
        for (DcMotor motor : motors) motor.setPower(speed);
        while (!whenToStop.value() && eTime.seconds() <= timeout) {} //Wait n chill

        //Reset
        for (DcMotor motor : motors) {
            motor.setPower(0);
            motor.setMode(motorModes.get(motor));
        }
        motorModes.clear();
    }

    /**
     * Drives forward a set amount of inches using encoders.
     *
     * The gear ratio should be < 1.0 if the wheel is geared up
     * (Bigger gear on motor, smaller gear on wheel)
     *
     * AndyMark motors have 1220 measuring ticks (counts) per revolution,
     * while the default is assumed to be 1440 (Tetrix)
     *
     * Motors are reset to their original mode, with 0 power
     *
     * This method will time out after timeout seconds has passed
     *
     * @param inches the number of inches to drive forward
     * @param gearRatio the gear ratio of the wheel
     * @param wheelDiameter the diameter of the wheels, in inches
     * @param isAndyMark whether the motors are AndyMark or not
     * @param speed the speed / power to run the motors at (can be negative)
     * @param motors the list of motors to run
     */
    // TODO make work
    public static void driveForwardWithEncoders(double inches, double gearRatio, double wheelDiameter, boolean isAndyMark,
                                         double speed, DcMotor... motors) {

        //Null check
        if (inches == 0 || gearRatio <= 0 || wheelDiameter <= 0 || speed == 0 || motors.length == 0) return;

        //Save the current run modes of the motors and reset the motors
        HashMap<DcMotor, DcMotor.RunMode> motorModes = new HashMap<>();
        for (DcMotor motor : motors) {
            motorModes.put(motor, motor.getMode());
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        //Calculate stuff
        double motorTicksPerRevolution = (isAndyMark) ? 1220 : 1440; //Default (Tetrix) is 1440, AndyMark is 1220
        double motorTicksPerInch = (motorTicksPerRevolution * gearRatio) / (wheelDiameter * Math.PI);
        int ticksToMove = (int) (motorTicksPerInch * inches); //Number of ticks to move motor

        //Do actual moving
        ElapsedTime eTime = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        eTime.reset();
        for (DcMotor motor : motors) {
            motor.setTargetPosition(motor.getCurrentPosition() + ticksToMove);
            motor.setPower(speed);
        }
        while (motors[motors.length - 1].isBusy() && eTime.seconds() <= timeout) {} //Chill

        //Cleanup
        for (DcMotor motor : motors) {
            motor.setPower(0);
            motor.setMode(motorModes.get(motor));
        }
        motorModes.clear();
    }

    /**
     * Calibrates a gyro, with a set timeout otherwise
     *
     * @param gyro the gyro to calibrate and reset
     */
    public static void calibrateGyro(ModernRoboticsI2cGyro gyro) {
        ElapsedTime elapsedTime = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        gyro.calibrate();
        while (gyro.isCalibrating() && elapsedTime.seconds() <= timeout) {}
        gyro.resetZAxisIntegrator();
    }

}
