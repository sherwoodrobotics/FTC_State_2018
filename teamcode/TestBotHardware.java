package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by kyledufrene on 3/14/17.
 */

public class TestBotHardware {

        /* Public OpMode members. */
        public DcMotor leftMotor   = null;
        public DcMotor  rightMotor  = null;



        HardwareMap hwMap           =  null;
        private ElapsedTime period  = new ElapsedTime();

        /* Constructor */
        public TestBotHardware(){

        }

        /* Initialize standard Hardware interfaces */
        public void init(HardwareMap ahwMap) {
            // Save reference to Hardware map
            hwMap = ahwMap;

            // Define and Initialize Motors
            leftMotor = hwMap.dcMotor.get("left_drive");
            rightMotor = hwMap.dcMotor.get("right_drive");


            // Set all motors to zero power
            leftMotor.setPower(0);
            rightMotor.setPower(0);


            // Set all motors to run without encoders.
            // May want to use RUN_USING_ENCODERS if encoders are installed.
            leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        }

        /***
         *
         * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
         * periodic tick.  This is used to compensate for varying processing times for each cycle.
         * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
         *
         * @param periodMs  Length of wait cycle in mSec.
         */
        public void waitForTick(long periodMs) {

            long  remaining = periodMs - (long)period.milliseconds();

            // sleep for the remaining portion of the regular cycle period.
            if (remaining > 0) {
                try {
                    Thread.sleep(remaining);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // Reset the cycle clock for the next pass.
            period.reset();
        }
    }



