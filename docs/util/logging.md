# Logging
## Usage in your class
Logging is in the `com.github.mmm.easyop.util.logging.Logger` mixin interface.

## Enumerations
### `com.github.mmm.easyop.util.logging.LogPriority`
Values:
- Assert
- Debug
- Error
- Info
- Verbose
- Warn

## Methods
### `log(LogPriority? priority, String message`
Logs `message` to the Android `logcat` system. If `priority` is not specified, the default of `LogPriority.Info` is used.

