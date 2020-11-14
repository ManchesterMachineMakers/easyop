package com.github.mmm.easyop;

public interface OpMode extends Base {
    void opmode(OpModeStage init, OpModeStage beforeLoop, OpModeStage loop);
    void opInit();
    void opBeforeLoop();
    void opLoop();
}
