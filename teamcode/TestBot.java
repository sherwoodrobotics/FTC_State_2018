package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;




@TeleOp(name="TestBot", group="SHS")

public class TestBot extends OpMode{

    /* Declare OpMode members. */
    TestBotHardware robot       = new TestBotHardware();



    @Override
    public void init() {
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello SHS Driver");    //
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }


    @Override
    public void loop() {

        double left;
        double right;
        double col;
        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;





        robot.leftMotor.setPower(left);
        robot.rightMotor.setPower(right);


    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }


}
