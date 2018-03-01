package org.firstinspires.ftc.teamcode.marvframework;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.marvframework.utils.MarvHardwareMap;

/**
 * Created by andy on 4/6/17.
 *
 * Base class for all TeleOp OpModes
 * All TeleOp modes should inherit from this
 *
 * Remember to add @TeleOp(name = "")
 * Before the class declaration
 */

@SuppressWarnings("unused")
public abstract class MarvTeleOp extends OpMode {

    /**
     * Gets the MarvHardwareMap associated
     *
     * This is used to configure the underlying OpMode hardwareMap
     *
     * @return the MarvHardwareMap for the OpMode
     */
    public abstract MarvHardwareMap getHardwareMap(); // Addressed in sub classes

    /**
     * Initiates the robot when "INIT" is pressed on driver control
     */
    @Override
    public void init() {
        getHardwareMap().setHardwareMap(hardwareMap);
        getHardwareMap().init();
        initTeleOp();

        //Gamepad controllers
        gamepad1.reset();
        gamepad2.reset();

        telemetry.setAutoClear(false);
        log("TeleOp initiated");
    }

    /**
     * Initiates TeleOp hardware
     */
    public abstract void initTeleOp(); // Addressed in sub classes

    /**
     * Run once when the play button is pressed on driver control
     */
    @Override
    public void start() {}

    /**
     * Continuously executed after the play button is pressed on driver control
     */
    @Override
    public abstract void loop(); // Addressed in sub classes

    /**
     * Run once when the stop button is pressed on driver control
     */
    @Override
    public abstract void stop(); // Addressed in sub classes

    /**
     * Waits a specified amount of time before returning
     *
     * This method is not thread-safe
     *
     * @param secondsToWait number of seconds to wait (can be decimal)
     */
    protected final void Wait(double secondsToWait) {
        getHardwareMap().Wait(secondsToWait);
    }

    /**
     * Logs a message to the telemetry, using the captopn "MarvTeleOp"
     *
     * @param message the message to log
     */
    protected void log(String message) {
        telemetry.addData("MarvTeleOp", message);
        telemetry.update();
    }
}
