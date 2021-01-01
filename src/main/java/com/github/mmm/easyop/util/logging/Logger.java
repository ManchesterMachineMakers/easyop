package com.github.mmm.easyop.util.logging;
import android.util.Log;

public interface Logger {
	default void log(String message) {
		log(LogPriority.Info, message);
	}
	default void log(LogPriority priority, String message) {
		Class<?> cls = this.getClass();
		String name = cls.getName();
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