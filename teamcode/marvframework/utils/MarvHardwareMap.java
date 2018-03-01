package org.firstinspires.ftc.teamcode.marvframework.utils;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by andy on 4/5/17.
 *
 * Base class to define all the specific hardware for a single robot.
 * This is not an OpMode, but rather a utility class.
 * All hardware maps should inherit from this
 *
 * Declare all hardware in this class
 */

@SuppressWarnings("unused")
public abstract class MarvHardwareMap {

    /**
     * Our connection to the bot's hardware
     */
    private HardwareMap hardwareMap = null;

    /**
     * What to use for logging
     */
    private Telemetry telemetry;

    /**
     * Used when Wait(secs) is called
     */

    private ElapsedTime eTime = new ElapsedTime(ElapsedTime.Resolution.SECONDS); //Used by OpModes
    private String hardwareMapName = "";

    /**
     * A blank constructor to make a new MarvHardwareMap
     */
    public MarvHardwareMap(Telemetry telemetry, String hardwareMapName) {
        setTelemetry(telemetry, hardwareMapName);
        log("MarvHardwareMap set up for " + hardwareMapName);
    }

    /**
     * Sets the logging telemetry to used. This is automatically called after construction
     *
     * @param telemetry the telemetry to use
     * @param hardwareMapName the name of the HardwareMap to use for logging
     */
    protected void setTelemetry(Telemetry telemetry, String hardwareMapName) {
        this.telemetry = telemetry;
        this.hardwareMapName = hardwareMapName;
        telemetry.setAutoClear(false);
        log("Telemetry setup for " + hardwareMapName);
    }

    private void log(String message) {
        telemetry.addData(hardwareMapName, message);
        telemetry.update();
    }

    /**
     * Gets the associated HardwareMap, set in {@link MarvHardwareMap#setHardwareMap(HardwareMap)}
     * @return the HardwareMap
     */
    protected HardwareMap getHardwareMap() {
        return hardwareMap;
    }

    /**
     * Gets a DcMotor on the bot. If none is found, null is returned
     *
     * @param name the configuration name of the DcMotor
     * @return the DcMotor
     */
    protected DcMotor getDcMotor(String name) {
        try {
            return getHardwareMap().dcMotor.get(name);
        } catch (NullPointerException npe) {
            log("Error: could not find DcMotor with name " + name);
            return null;
        }
    }

    /**
     * Gets a Servo on the bot. If none is found, null is returned
     *
     * @param name the configuration name of the Servo
     * @return the Servo
     */
    protected Servo getServo(String name) {
        try {
            return getHardwareMap().servo.get(name);
        } catch (NullPointerException npe) {
            log("Error: could not find Servo with name " + name);
            return null;
        }
    }

    /**
     * Gets a ModernRoboticsI2cGyro on the bot. If none is found, null is returned
     *
     * @param name the configuration name of the ModernRoboticsI2cGyro
     * @return the ModernRoboticsI2cGyro
     */
    protected ModernRoboticsI2cGyro getGyro(String name) {
        try {
            return (ModernRoboticsI2cGyro) getHardwareMap().gyroSensor.get(name);
        } catch (NullPointerException npe) {
            log("Error: could not find ModernRoboticsI2cGyro with name " + name);
            return null;
        }
    }

    /**
     * Gets a ColorSensor on the bot. If none is found, null is returned
     *
     * @param name the configuration name of the ColorSensor
     * @return the ColorSensor
     */
    protected ColorSensor getColorSensor(String name) {
        try {
            return getHardwareMap().colorSensor.get(name);
        } catch (NullPointerException npe) {
            log("Error: could not find ColorSensor with name " + name);
            return null;
        }
    }

    /**
     * Sets the HardwareMap to use
     *
     * @param botHardwareMap the HardwareMap to use
     */
    public final void setHardwareMap(HardwareMap botHardwareMap) {
        hardwareMap = botHardwareMap;
        log("HardwareMap set");
    }

    /**
     * Initiates the MarvHardwareMap and configures electronics
     */
    public abstract void init(); // Addressed in sub classes

    /**
     * Waits a specified amount of time before returning
     *
     * This method is not thread-safe
     *
     * @param secondsToWait number of seconds to wait (can be decimal)
     */
    @SuppressWarnings("StatementWithEmptyBody")
    public final void Wait(double secondsToWait) {
        eTime.reset();
        while (eTime.seconds() < secondsToWait) {
            //Stall
        }
    }
}