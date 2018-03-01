package org.firstinspires.ftc.teamcode.demo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.marvframework.MarvAutonomous;
import org.firstinspires.ftc.teamcode.marvframework.utils.MarvHardwareMap;
import org.firstinspires.ftc.teamcode.marvframework.utils.MarvUtils;

/**
 * Created by andy on 4/11/17.
 *
 * Progressively slows down during forward auto drive
 */

@Autonomous(name = "DemoAutoGOProgressive")
@Disabled
public class DemoAutoGOProgressive extends MarvAutonomous{

    private DemoHardware hardware = DemoHardware.getDemoHardware(telemetry);

    private DcMotor[] motors = new DcMotor[2];

    @Override
    public MarvHardwareMap getHardwareMap() {
        return hardware;
    }

    @Override
    public int getAutonomousLength() { return 15; } //Seconds

    @Override
    public void initAutonomous() {
        motors[0] = hardware.leftWheel;
        motors[1] = hardware.rightWheel;
    }

    @Override
    public void runAutonomous() {
        MarvUtils.driveForwardForTime(1, 1.0, motors);
        MarvUtils.driveForwardForTime(1, 0.9, motors);
        MarvUtils.driveForwardForTime(1, 0.8, motors);
        MarvUtils.driveForwardForTime(1, 0.7, motors);
        MarvUtils.driveForwardForTime(1, 0.6, motors);
        MarvUtils.driveForwardForTime(1, 0.5, motors);
        MarvUtils.driveForwardForTime(1, 0.4, motors);
        MarvUtils.driveForwardForTime(1, 0.3, motors);
        MarvUtils.driveForwardForTime(1, 0.2, motors);
        MarvUtils.driveForwardForTime(1, 0.1, motors);

        log("Autonomous done");
    }

    @Override
    public void stopAutonomous() {
        for (DcMotor motor : motors) motor.setPower(0);
    }
}
