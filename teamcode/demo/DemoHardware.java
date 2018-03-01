package org.firstinspires.ftc.teamcode.demo;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.marvframework.utils.MarvHardwareMap;

/**
 * Created by andy on 4/7/17.
 *
 * DemoBot hardware map
 * Two wheel drive and that's it
 */

public class DemoHardware extends MarvHardwareMap {

    //Only one instance of DemoHardware is kept this way
    public static DemoHardware demoHardware;
    public static DemoHardware getDemoHardware(Telemetry telemetry) {
        if (demoHardware == null) demoHardware = new DemoHardware(telemetry);
        demoHardware.setTelemetry(telemetry, "DemoHardware");
        return demoHardware;
    }

    public DcMotor leftWheel = null;
    public DcMotor rightWheel = null;

    public DemoHardware(Telemetry telemetry) {
        super(telemetry, "DemoHardware");
    }

    @Override
    public void init() {
        leftWheel  = getDcMotor("left_wheel");
        rightWheel = getDcMotor("right_wheel");

        leftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
        rightWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        leftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
