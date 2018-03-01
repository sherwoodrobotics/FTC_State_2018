package org.firstinspires.ftc.teamcode.demo;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.marvframework.utils.MarvHardwareMap;
import org.firstinspires.ftc.teamcode.marvframework.MarvTeleOp;

/**
 * Created by andy on 4/7/17.
 *
 * Classic 2 joystick tank drive teleop
 * Left joystick up/down = left wheels up/down
 * Right joystick up/down = right wheels up/down
 */

@TeleOp(name = "DemoTeleOpClassicTank")
@Disabled
public class DemoTeleOpClassicTank extends MarvTeleOp {

    private DemoHardware hardware = DemoHardware.getDemoHardware(telemetry);

    //We don't want local variables in a loop
    private float inputLeftWheels = 0, inputRightWheels = 0;

    @Override
    public MarvHardwareMap getHardwareMap() {
        return hardware;
    }

    public void initTeleOp() { }

    @Override
    public void loop() {
        inputLeftWheels = -gamepad1.left_stick_y;
        inputRightWheels = -gamepad1.right_stick_y;

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
