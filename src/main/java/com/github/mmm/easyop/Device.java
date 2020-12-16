package com.github.mmm.easyop;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
public @interface Device {
    String value() default "";
}