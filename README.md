# Reproducer for [gradle/gradle#29113](https://github.com/gradle/gradle/issues/29113)

This project demonstrates a bug in JDK8 (untested below JDK8), where an externally managed `Context` object becomes invalid if any annotation processor is added to the compiler task.

Search for `BUG: ` in the code to find the relevant parts.

## Running the reproducer

Execute the following command to run the reproducer:
```shell
./gradlew run
```

The output should be:
```
> Task :App.main()
Java version: 1.8.0_412
===
Failed to rich format diagnostic message
Compilation succeeded
===
```

## Tested environment

OS: 
 - MacOS 14.5 (23F79)

JDK:
 - OpenJDK 1.8.0_412 (multiple vendors, fails)
 - OpenJDK 11.0.22 (multiple vendors, works)