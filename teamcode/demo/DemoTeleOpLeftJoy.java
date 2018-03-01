package org.firstinspires.ftc.teamcode.demo;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.marvframework.MarvTeleOp;
import org.firstinspires.ftc.teamcode.marvframework.utils.MarvHardwareMap;

/**
 * Created by andy on 4/11/17.
 *
 * Left joystick turning driving
 * Up = forward
 * Left = turn left
 * Right = turn right
 * Down = backward
 */

@TeleOp(name = "DemoTeleOpLeftJoy")
@Disabled
public class DemoTeleOpLeftJoy extends MarvTeleOp {

    private DemoHardware hardware = DemoHardware.getDemoHardware(telemetry);

    //Temporary input reading
    private float leftWheel, rightWheel;

    @Override
    public MarvHardwareMap getHardwareMap() {
        return hardware;
    }

    @Override
    public void initTeleOp() { }

    @Override
    public void loop() {
        if (Math.abs(gamepad1.left_stick_x) >= 0.2) {
            leftWheel = gamepad1.left_stick_x;
            rightWheel = -gamepad1.left_stick_x;
        } else {
            leftWheel = -gamepad1.left_stick_y;
            rightWheel = -gamepad1.left_stick_y;
        }

        hardware.leftWheel.setPower(leftWheel);
        hardware.rightWheel.setPower(rightWheel);
    }

    @Override
    public void stop() {
        leftWheel = 0;
        rightWheel = 0;
        hardware.leftWheel.setPower(leftWheel);
        hardware.rightWheel.setPower(rightWheel);
    }

}
