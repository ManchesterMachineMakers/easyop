package com.github.mmm.easyop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.lang.reflect.*;
import java.lang.annotation.Annotation;

public interface Base {
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
    default void subassemblies() throws IllegalAccessException, InstantiationException {
        Class<?> cls = this.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            for (Annotation anno : field.getDeclaredAnnotations()) {
                if (anno.annotationType() == UseSubassembly.class) {
                    Subassembly sa = (Subassembly) field.getType().newInstance();
                    sa.init(((OpMode) this).hardwareMap);
                    field.set(this, sa);
                }
            }
        }
    }
}