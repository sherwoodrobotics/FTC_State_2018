package org.firstinspires.ftc.teamcode.templates;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.marvframework.utils.MarvHardwareMap;

/**
 * Created by andy on 4/20/17.
 *
 * Free copy paste for creating MarvHardwareMaps
 */

public class TemplateHardwareMap extends MarvHardwareMap {

    //Change this to whatever the class name is
    private static final String className = "TemplateHardwareMap";

    /*
     * By calling TemplateHardwareMap.getHardwareMap(), you can keep
     * one instance of HardwareMap across multiple OpModes.
     * Don't touch this code, just use it
     */
    private static TemplateHardwareMap hardwareMap;
    public static TemplateHardwareMap getHardwareMap(Telemetry telemetry) {
        if (hardwareMap == null) hardwareMap = new TemplateHardwareMap(telemetry);
        hardwareMap.setTelemetry(telemetry, className);
        return hardwareMap;
    }

    /*
     *
     *
     * Declare hardware parts here, like DcMotor, Servo, ColorSensor, etc
     *
     *
     */

    public TemplateHardwareMap(Telemetry telemetry) {
        super(telemetry, className);
    }

    @Override
    public void init() {
        /*
         * Assign electronics here, by using getServo, getGyro, getColorSensor, getDcMotor, etc
         *
         * Electronics may be configured here as well, like DcMotor direction, Encoders, etc
         * Configurations here will apply through all OpModes
         */
    }
}
