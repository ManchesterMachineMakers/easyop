package com.github.mmm.easyop;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.HashMap;

public interface Subassembly extends Base, Injectable {
    default void init(HashMap<String, ?> args) throws ReflectiveOperationException {
        HardwareMap hwMap = (HardwareMap)args.get("hwMap");
        devices(hwMap);
        subassemblies(hwMap);
    }
}
