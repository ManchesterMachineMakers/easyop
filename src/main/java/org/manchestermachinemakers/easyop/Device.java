package org.manchestermachinemakers.easyop;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
public @interface Device {
    String value() default "";
}