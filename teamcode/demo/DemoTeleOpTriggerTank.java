package org.firstinspires.ftc.teamcode.demo;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.marvframework.MarvTeleOp;
import org.firstinspires.ftc.teamcode.marvframework.utils.MarvHardwareMap;

/**
 * Created by andy on 4/12/17.
 *
 * Left and right triggers to drive
 * Left = veer left (right wheels)
 * Right = veer right (left wheels)
 */

@TeleOp(name = "DemoTeleOpTriggerTank")
@Disabled
public class DemoTeleOpTriggerTank extends MarvTeleOp {

    private DemoHardware hardware = DemoHardware.getDemoHardware(telemetry);

    //We don't want local variables in a loop
    private float inputLeftWheels = 0, inputRightWheels = 0;

    @Override
    public MarvHardwareMap getHardwareMap() {
        return hardware;
    }

    @Override
    public void initTeleOp() { }

    @Override
    public void start() {
        log("TeleOp starting");
    }

    @Override
    public void loop() {
        inputLeftWheels = gamepad1.right_trigger;
        inputRightWheels = gamepad1.left_trigger;

        hardware.leftWheel.setPower(inputLeftWheels);
        hardware.rightWheel.setPower(inputRightWheels);
    }

    @Override
    public void stop() {
        inputLeftWheels = 0;
        inputRightWheels = 0;
        hardware.leftWheel.setPower(inputLeftWheels);
        hardware.rightWheel.setPower(inputRightWheels);
    }
}