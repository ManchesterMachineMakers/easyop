package org.manchestermachinemakers.easyop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public interface Injectable {
    void init(HashMap<String, ?> params) throws ReflectiveOperationException;
    HashMap<Class<?>, Injectable> globals = new HashMap<>();
    static <T, I extends Injectable> void injectAll(T obj, Class<I> tInject) throws ReflectiveOperationException {
        injectAll(obj, tInject, new HashMap<>());
    }
    static <T, I extends Injectable> void injectAll(T obj, Class<I> tInject, HashMap<String, ?> params) throws ReflectiveOperationException {
        Class<T> cls = (Class<T>) obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for(Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation anno : annotations) {
                if(anno.annotationType() == Inject.class) {
                    Class<?> tField = ((Inject)anno).value();
                    if(tField == Object.class) tField = field.getType();
                    if(tInject.isAssignableFrom(tField)) {
                        if (!globals.containsKey(tField)) {
                            Injectable instance = (Injectable) tField.newInstance();
                            instance.init(params);
                            globals.put(field.getType(), instance);
                        }
                        field.set(obj, field.getType().cast(globals.get(field.getType())));
                    }
                }
            }
        }
    }
}
