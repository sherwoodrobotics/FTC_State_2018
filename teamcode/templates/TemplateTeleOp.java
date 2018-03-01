package org.firstinspires.ftc.teamcode.templates;

import org.firstinspires.ftc.teamcode.marvframework.MarvTeleOp;
import org.firstinspires.ftc.teamcode.marvframework.utils.MarvHardwareMap;

/**
 * Created by andy on 4/20/17.
 *
 * Free copypaste for TeleOp
 */

//Uncomment this to let this class be recognized as an OpMode
//@TeleOp(name = "TemplateTeleOp")

public class TemplateTeleOp extends MarvTeleOp {

    //Extend MarvHardwareMap in a separate class
    public MarvHardwareMap hardwareMap = null;

    @Override
    public MarvHardwareMap getHardwareMap() {
        return hardwareMap;
    }

    @Override
    public void initTeleOp() {
        /*
         * Initiate hardware from the MarvHardwareMap here (Servos, DcMotors, etc)
         */
    }

    @Override
    public void loop() {
        /*
         * Loops repeatedly until stop is requested
         * Poll controller input and update hardware here
         */
    }

    @Override
    public void stop() {
        /*
         * Stop all hardware here, i.e. motor.setPower(0)
         */
    }
}
