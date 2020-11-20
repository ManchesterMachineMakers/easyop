package com.github.mmm.easyop;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.lang.reflect.*;
import java.lang.annotation.Annotation;
import java.util.HashMap;

public interface Base {
    default void devices(HardwareMap hwMap) throws ReflectiveOperationException {
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
    default void subassemblies(HardwareMap hwMap) throws ReflectiveOperationException {
        HashMap<String, HardwareMap> args = new HashMap<>();
        args.put("hwMap", hwMap);
        Injectable.injectAll(this, Subassembly.class, args);
    }
}