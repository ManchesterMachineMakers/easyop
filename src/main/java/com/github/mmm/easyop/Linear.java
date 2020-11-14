package com.github.mmm.easyop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class Linear extends LinearOpMode implements Base {
    public void opmode(OpModeStage init, OpModeStage beforeLoop, OpModeStage loop) {
        try {
            devices();
        } catch(IllegalAccessException e) {
            return;
        }
        init.apply();
        waitForStart();
        if(opModeIsActive()) {
            beforeLoop.apply();
            while(opModeIsActive()) {
                loop.apply();
            }
        }
    }
    public void runOpMode() {
        opmode(this::opInit, this::opBeforeLoop, this::opLoop);
    }
}