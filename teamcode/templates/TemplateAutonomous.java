package org.firstinspires.ftc.teamcode.templates;

import org.firstinspires.ftc.teamcode.marvframework.MarvAutonomous;
import org.firstinspires.ftc.teamcode.marvframework.utils.MarvHardwareMap;

/**
 * Created by andy on 4/20/17.
 *
 * Free copypaste for Autonomous
 */

//Uncomment this to let this class be recognized as an OpMode
//@Autonomous(name = "TemplateAutonomous")

public class TemplateAutonomous extends MarvAutonomous {

    //Extend MarvHardwareMap in a separate class
    public MarvHardwareMap hardwareMap = null;

    @Override
    public MarvHardwareMap getHardwareMap() {
        return hardwareMap;
    }

    @Override
    public int getAutonomousLength() {
        return 15; //FTC Autonomous is 15 seconds long
    }

    @Override
    public void initAutonomous() {
        /*
         * Initiate hardware from the MarvHardwareMap here (Gyros, Servos, etc)
         */
    }

    @Override
    public void runAutonomous() {
        /*
         * Hardware initiation (Servos, Gyros, etc) is declared in the MarvHardwareMap
         *
         * Place all Autonomous code here
         * Be sure to make use of shouldStop() frequently
         * This method is run once
         */
    }

    @Override
    public void stopAutonomous() {
        /*
         * Stop all hardware here, i.e. motor.setPower(0)
         */
    }
}
