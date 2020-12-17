# EasyOp
© 2020 FTC Team 16221 Manchester Machine Makers

## Installation
```sh-script
curl https://manchestermachinemakers.github.io/easyop/install.sh | sh
```

## What is it?
EasyOp is a library for modularizing FTC code, and writing it more easily at that.

## What has it got in its pocketses?
Fishbones, goblins' teeth, wet shells, a bit of bat-wing, a sharp stone to sharpen its fangs on, and other nasty things.

## How do I use this library?
### Creating an opmode
Create a Java file with the following content in the usual `TeamCode/src/main/java/org/firstinspires/ftc/teamcode` directory with the following template content:
```java
package org.firstinspires.ftc.teamcode;

import com.github.mmm.easyop.Linear;
import com.github.mmm.easyop.Inject;
import com.github.mmm.easyop.Device;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "OpModeName")
// or
@TeleOp(name = "OpModeName")
public class OpModeName extends Linear {

    public void opInit() {
    }

    public void opBeforeLoop() {
    }

    public void opLoop() {
    }
}
```
And customize to your liking. Remember to pick *one* of either `@Autonomous` or `@TeleOp`!

### Implementing a subassembly
Our team uses a `subassemblies` directory under the main source directory, but you can do whatever you want. \
Create a Java file in whichever directory you choose, with the following template content:
```java
package org.firstinspires.ftc.teamcode.subassemblies;

import com.github.mmm.easyop.Device;
import com.github.mmm.easyop.Inject;
import com.github.mmm.easyop.Subassembly;

public class SubassemblyName implements Subassembly {
    // Implementation goes here
}
```
And customize to your liking. Subassembly classes can have whatever methods and members you want - there are no mandatory interface methods, the `Subassembly` interface just has some `default`-marked methods to help with dependency injection.

### Using a subassembly
First, make sure your subassembly class is imported, e.g.:
```java
import org.firstinspires.ftc.teamcode.subassemblies.*;
```
Then, make sure the class you want to inject the subassembly into is either a derivatve of `com.github.mmm.easyop.OpMode` or `com.github.mmm.easyop.Subassembly` (`Linear` is derived from `OpMode`, so the opmode example above does allow you to use EasyOp's dependency injection system). \
Add a `public` member with the `@Inject` attribute, like so:
```java
@Inject
public SubassemblyClass subassemblyName;
```
(replacing `SubassemblyClass` with the subassembly class name) \
And it will be automatically initialized when your OpMode is initialized.
### Using a device
First, as before, make sure the class you want to use a device in derives from either `OpMode` or `Subassembly`. Then, add a `public` member with the `@Device` attribute, like so:
```java
@Device
public DeviceClass deviceName;
```
(replacing `DeviceClass` with the `com.qualcomm.robotcore.hardware` device class, e.g. `DcMotor`. and `deviceName` with the name of the device in the robot controller configuration) \
And it will be automatically loaded from the `hardwareMap` when your opmode is initialized.

## Class, interface & attribute reference
### `interface Subassembly`
This is the base interface for subassemblies.
### `interface OpMode`
This is the base interface for EasyOp opmodes. ***DO NOT*** subclass this directly. It will not do what you think. If you _really_ want to subclass this directly, use it in conjunction with a `com.qualcomm.robotcore.eventloop` opmode class. For example:
```java
class MyOpMode extends com.qualcomm.robotcore.eventloop.OpMode implements com.github.mmm.easyop.OpMode {
```
### `abstract class Linear`
This is the base class for linear opmodes. \
**You must implement:**
```java
public void opInit(); // Run after the 'Init' button has been tapped and devices and subassemblies have been initialized
public void opBeforeLoop(); // Run when your opmode has started running, but before the main loop
public void opLoop(); // Run repeatedly until the opmode is stopped.
```
### `@Inject(optional Class<?> tInject)`
**Retention:** Runtime \
Injects a subassembly of the type specified by `tInject` or the type of the member it is applied to. \
**Parameters:** \
`tInject`: (optional) Subassembly class to inject. \
**Examples:**
```java
@Inject // type is inferred from variable
public DriveBase driveBase;
```
```java
@Inject(DriveBase.class) // type is explicitly defined
public Subassembly driveBase;
```

### `@Device(optional String name)`
**Retention:** Runtime \
Injects the device from the `hardwareMap` whose type matches the type of the member this annotation is applied to and whose name matches either `name`, or, if not specified, the name of the member that it is applied to. \
**Parameters**: \
`name`: (optional) Name of the device to inject. \
**Examples:** \
```java
@Device // name is inferred from variable
public DcMotor motorL;
```
```java
@Device("motorL") // name is explicitly passed, variable name can be anything
public DcMotor theMotor;
```