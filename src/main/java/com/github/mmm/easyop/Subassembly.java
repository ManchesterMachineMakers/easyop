package com.github.mmm.easyop;
import com.qualcomm.robotcore.hardware.HardwareMap;

public interface Subassembly extends Base {
    default void init(HardwareMap hwMap) throws IllegalAccessException, InstantiationException {
        devices();
        subassemblies();
    }
}
