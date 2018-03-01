package org.firstinspires.ftc.teamcode.demo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.marvframework.MarvAutonomous;
import org.firstinspires.ftc.teamcode.marvframework.utils.MarvHardwareMap;
import org.firstinspires.ftc.teamcode.marvframework.utils.MarvUtils;

/**
 * Created by andy on 4/11/17.
 *
 * Autonomous that moves forward, full power, for 3 seconds
 */

@Autonomous(name = "DemoAutoGO")
@Disabled
public class DemoAutoGO extends MarvAutonomous {

    private DemoHardware hardware = DemoHardware.getDemoHardware(telemetry);

    @Override
    public MarvHardwareMap getHardwareMap() {
        return hardware;
    }

    @Override
    public int getAutonomousLength() { return 15; } //Seconds

    @Override
    public void initAutonomous() {
    }

    @Override
    public void runAutonomous() {
        MarvUtils.driveForwardForTime(3, 1.0, hardware.leftWheel, hardware.rightWheel);

        log("Autonomous done");
    }

    @Override
    public void stopAutonomous() {
        hardware.leftWheel.setPower(0);
        hardware.rightWheel.setPower(0);
    }
}
