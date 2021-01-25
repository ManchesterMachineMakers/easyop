package com.github.mmm.easyop;

public interface OpMode extends Base {
    void opmode(OpModeStage init, OpModeStage beforeLoop, OpModeStage loop, OpModeStage afterLoop);
    default void opInit() {};
    default void opBeforeLoop() {};
    default void opLoop() {};
    default void opAfterLoop() {};
}
