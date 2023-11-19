package org.manchestermachinemakers.easyop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.RobotLog;

public abstract class Linear extends LinearOpMode implements OpMode {
    public void opmode(OpModeStage init, OpModeStage beforeLoop, OpModeStage loop, OpModeStage afterLoop) {
        try {
            devices(hardwareMap);
            subassemblies(this);
        } catch(Exception e) {
            RobotLog.i("Error in initialization: " + e.toString());
            return;
        }
        init.apply();
        waitForStart();
        if(opModeIsActive()) {
            beforeLoop.apply();
            while(opModeIsActive()) {
                loop.apply();
            }
            afterLoop.apply();
        }
    }
    public void runOpMode() {
        opmode(this::opInit, this::opBeforeLoop, this::opLoop, this::opAfterLoop);
    }
}