package com.github.mmm.easyop.util.logging;
import android.util.Log;
import java.lang.annotation.Annotation;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

public interface Logger {
	default void log(String message) {
		log(LogPriority.Info, message);
	}
	default void log(LogPriority priority, String message) {
		Class<?> cls = this.getClass();
		Annotation[] annotations = cls.getDeclaredAnnotations();
		String name = cls.getName();
		for(Annotation anno : annotations) {
			if(anno.annotationType() == Autonomous.class) {
				name = ((Autonomous)anno).name();
				break;
			} else if(anno.annotationType() == TeleOp.class) {
				name = ((TeleOp)anno).name();
			}
		}
		int realPriority = Log.INFO;
		switch(priority) {
			case Assert:
				realPriority = Log.ASSERT;
				break;
			case Debug:
				realPriority = Log.DEBUG;
				break;
			case Error:
				realPriority = Log.ERROR;
				break;
			case Info:
				realPriority = Log.INFO;
				break;
			case Verbose:
				realPriority = Log.VERBOSE;
				break;
			case Warn:
				realPriority = Log.WARN;
				break;
		}
		Log.println(realPriority, name, message);
	}
}