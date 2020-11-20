package com.github.mmm.easyop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public interface Injectable {
    void init(HashMap<String, ?> params) throws ReflectiveOperationException;
    public static HashMap<Class<?>, Injectable> globals = new HashMap<>();
    public static <T, I extends Injectable> void injectAll(T obj, Class<I> tInject) throws ReflectiveOperationException {
        injectAll(obj, tInject, new HashMap<>());
    }
    public static <T, I extends Injectable> void injectAll(T obj, Class<I> tInject, HashMap<String, ?> params) throws ReflectiveOperationException {
        Class<T> cls = (Class<T>) obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for(Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation anno : annotations) {
                if(anno.annotationType() == Inject.class && tInject.isAssignableFrom(field.getType())) {
                    if(!globals.containsKey(field.getType())) {
                        Injectable instance = (Injectable) field.getType().newInstance();
                        instance.init(params);
                        globals.put(field.getType(), instance);
                    }
                    field.set(obj, field.getType().cast(globals.get(field.getType())));
                }
            }
        }
    }
}
