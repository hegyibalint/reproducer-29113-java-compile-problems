plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    `java-library`
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

val jdk8JavaCompiler = javaToolchains.compilerFor {
    languageVersion = JavaLanguageVersion.of(8)
}
val jdk8JavaLauncher = javaToolchains.launcherFor {
    languageVersion = JavaLanguageVersion.of(8)
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

val testedAnnotationProcessors = configurations.create("usedAnnotationProcessors")

dependencies {
    implementation(jdk8JavaCompiler.map {
        files(it.metadata.installationPath.file("lib/tools.jar"))
    })
    testedAnnotationProcessors("org.immutables:value:2.+")
}

tasks{
    register("run", JavaExec::class) {
        dependsOn("build")

        workingDir = file(rootProject.projectDir)

        mainClass = "org.example.App"
        classpath = sourceSets.main.get().runtimeClasspath

        args = listOf(
            testedAnnotationProcessors.files.first().toPath().toAbsolutePath().toString()
        )
    }
}
afterEvaluate {
    print(testedAnnotationProcessors.files)
}