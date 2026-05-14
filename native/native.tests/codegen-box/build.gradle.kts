import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.testing.Test

plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)

    testImplementation(projectTests(":native:native.tests"))
}

sourceSets {
    "main" { none() }
    "test" {
        projectDefault()
        generatedTestDir()
    }
}

val testTags = findProperty("kotlin.native.tests.tags")?.toString()
// Note: arbitrary JUnit tag expressions can be used in this property.
// See https://junit.org/junit5/docs/current/user-guide/#running-tests-tag-expressions
val test by nativeTest("test", testTags)

// Box fixtures are read from compiler/testData at runtime, not from the test classpath.
tasks.named<Test>("test") {
    inputs.dir(rootProject.layout.projectDirectory.dir("compiler/testData/codegen/box"))
        .withPathSensitivity(PathSensitivity.RELATIVE)
    inputs.dir(rootProject.layout.projectDirectory.dir("compiler/testData/codegen/boxInline"))
        .withPathSensitivity(PathSensitivity.RELATIVE)
}
