package org.manchestermachinemakers.easyop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.HashMap;

public interface Subassembly extends Base, Injectable {
    default void customInit(LinearOpMode opMode) {

    }
    default void init(HashMap<String, ?> args) throws ReflectiveOperationException {
        LinearOpMode opMode = (LinearOpMode) args.get("opMode");
        devices(opMode.hardwareMap);
        subassemblies(opMode);
        customInit(opMode);
    }
}
