package org.firstinspires.ftc.teamcode.demo;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.marvframework.MarvTeleOp;
import org.firstinspires.ftc.teamcode.marvframework.utils.MarvHardwareMap;

/**
 * Created by andy on 4/11/17.
 *
 * DPad turning & driving
 * Up = forward
 * Left = turn left
 * Right = turn right
 * Down = backward
 */

@TeleOp(name = "DemoTeleOpDPad")
@Disabled
public class DemoTeleOpDPad extends MarvTeleOp {

    private DemoHardware hardware = DemoHardware.getDemoHardware(telemetry);

    private static final float
            turnspeed = 0.6f,
            forwardspeed = 0.8f;

    //Temporary input reading
    private float leftWheel = 0, rightWheel = 0;

    @Override
    public MarvHardwareMap getHardwareMap() {
        return hardware;
    }

    public void initTeleOp() { }

    @Override
    public void loop() {
        if (gamepad1.dpad_left || gamepad1.dpad_right) {
            if (gamepad1.dpad_left && gamepad1.dpad_right) {
                leftWheel = 0;
                rightWheel = 0;
            } else if (gamepad1.dpad_left) {
                leftWheel = -turnspeed;
                rightWheel = turnspeed;
            } else {
                leftWheel = turnspeed;
                rightWheel = -turnspeed;
            }
        } else if (gamepad1.dpad_up || gamepad1.dpad_down) {
            if (gamepad1.dpad_up && gamepad1.dpad_down) {
                leftWheel = 0;
                rightWheel = 0;
            } else if (gamepad1.dpad_up) {
                leftWheel = forwardspeed;
                rightWheel = forwardspeed;
            } else {
                leftWheel = -forwardspeed;
                rightWheel = -forwardspeed;
            }
        } else {
            leftWheel = 0;
            rightWheel = 0;
        }

        hardware.leftWheel.setPower(leftWheel);
        hardware.rightWheel.setPower(rightWheel);
    }

    @Override
    public void stop() {
        leftWheel = 0;
        rightWheel = 0;
        hardware.leftWheel.setPower(0);
        hardware.rightWheel.setPower(0);
    }
}
