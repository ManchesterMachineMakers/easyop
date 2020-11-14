package com.github.mmm.easyop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.lang.reflect.*;
import java.lang.annotation.Annotation;

public interface Base {
    default void devices(HardwareMap hwMap) throws IllegalAccessException {
        Class<?> cls = this.getClass();
        Field[] fields = cls.getDeclaredFields();
        for(Field field : fields) {
            for(Annotation anno : field.getDeclaredAnnotations()) {
                if(anno.annotationType() == Device.class) {
                    field.set(this, hwMap.get(field.getType(), field.getName()));
                }
            }
        }
    }
    default void subassemblies(HardwareMap hwMap) throws IllegalAccessException, InstantiationException {
        Class<?> cls = this.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            for (Annotation anno : field.getDeclaredAnnotations()) {
                if (anno.annotationType() == UseSubassembly.class) {
                    Subassembly sa = (Subassembly) field.getType().newInstance();
                    sa.init(hwMap);
                    field.set(this, sa);
                }
            }
        }
    }
}