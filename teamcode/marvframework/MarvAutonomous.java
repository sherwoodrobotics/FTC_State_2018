package org.firstinspires.ftc.teamcode.marvframework;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.marvframework.utils.MarvHardwareMap;

/**
 * Created by andy on 4/7/17.
 *
 * Base class for all autonomous OpModes
 * All autonomous modes should inherit from this
 *
 * Remember to add @Autonomous(name = "")
 * Before the class declaration
 */

@SuppressWarnings("unused")
public abstract class MarvAutonomous extends LinearOpMode {

    /**
     * Indicates whether Autonomous mode should be stopped
     *
     * This will be true getAutonomousLength() seconds after starting Autonomous
     */
    private volatile boolean shouldStopAutonomous = false;

    /**
     * Gets the MarvHardwareMap associated
     *
     * This is used to configure the underlying OpMode hardwareMap
     *
     * @return the MarvHardwareMap for the OpMode
     */
    public abstract MarvHardwareMap getHardwareMap(); // Addressed in sub classes

    /**
     * Returns how long the Autonomous period lasts, in seconds
     *
     * @return the length of Autonomous
     */
    public abstract int getAutonomousLength(); // Addressed in sub classes

    @Override
    public void runOpMode() {
        //Initiation
        getHardwareMap().setHardwareMap(hardwareMap);
        getHardwareMap().init();
        initAutonomous();
        telemetry.setAutoClear(false);
        shouldStopAutonomous = false;
        log("Autonomous initiated");

        //Wait for OpMode begin
        waitForStart();

        //Autonomous stop flag
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(getAutonomousLength() * 1000);
                } catch (InterruptedException e) { /* Don't worry about it */ }
                shouldStopAutonomous = true;
            }
        }.start();

        //Do Autonomous
        runAutonomous();
        stopAutonomous();
    }

    /**
     * Initiates Autonomous hardware
     */
    public abstract void initAutonomous(); // Addressed in sub classes

    /**
     * Indicates whether the Autonomous mode should be stopped
     *
     * This will return true getAutonomousLength() seconds after starting Autonomous
     *
     * @return whether Autonomous mode should be stopped
     */
    public boolean shouldStop() {
        return shouldStopAutonomous || isStopRequested();
    }

    /**
     * Runs the Autonomous OpMode
     */
    public abstract void runAutonomous(); // Addressed in sub classes

    /**
     * Run when Autonomous ends
     */
    public abstract void stopAutonomous(); // Addressed in sub classes

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
     * Logs a message to the telemetry, using the caption "MarvTeleOp"
     *
     * @param message the message to log
     */
    protected void log(String message) {
        telemetry.addData("MarvAutonomous", message);
        telemetry.update();
    }
}
