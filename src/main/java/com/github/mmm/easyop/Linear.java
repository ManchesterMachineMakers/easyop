package com.github.mmm.easyop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class Linear extends LinearOpMode implements OpMode {
    public void opmode(OpModeStage init, OpModeStage beforeLoop, OpModeStage loop) {
        try {
            devices(hardwareMap);
            subassemblies(hardwareMap);
        } catch(Exception e) {
            System.out.println("Error in initialization: " + e.toString());
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