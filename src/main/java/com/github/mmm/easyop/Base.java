package com.github.mmm.easyop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.lang.reflect.*;
import java.lang.annotation.Annotation;

public interface Base {
    void opInit();
    void opBeforeLoop();
    void opLoop();
    default void devices() throws IllegalAccessException {
        Class<?> cls = this.getClass();
        Field[] fields = cls.getDeclaredFields();
        for(Field field : fields) {
            for(Annotation anno : field.getDeclaredAnnotations()) {
                if(anno.annotationType() == Device.class) {
                    field.set(this, ((OpMode)this).hardwareMap.get(field.getType(), field.getName()));
                }
            }
        }
    }
    void opmode(OpModeStage init, OpModeStage beforeLoop, OpModeStage loop);
}