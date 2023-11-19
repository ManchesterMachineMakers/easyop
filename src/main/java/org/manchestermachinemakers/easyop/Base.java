package org.manchestermachinemakers.easyop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.RobotLog;

import java.lang.reflect.*;
import java.lang.annotation.Annotation;
import java.util.HashMap;

public interface Base {
    default void devices(HardwareMap hwMap) throws ReflectiveOperationException {
        RobotLog.i("Injecting devices");
        Class<?> cls = this.getClass();
        Field[] fields = cls.getDeclaredFields();
        for(Field field : fields) {
            for(Annotation anno : field.getDeclaredAnnotations()) {
                if(anno.annotationType() == Device.class) {
                    String name = ((Device)anno).value();
                    if(name == "") name = field.getName();
                    RobotLog.i("- " + name);
                    field.set(this, hwMap.get(field.getType(), name));
                }
            }
        }
    }
    default void subassemblies(LinearOpMode self) throws ReflectiveOperationException {
        RobotLog.i("Injecting subassemblies");
        HashMap<String, Object> args = new HashMap<>();
        args.put("opMode", self);

        Injectable.injectAll(this, Subassembly.class, args);
    }
}