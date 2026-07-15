plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    id("org.jetbrains.kotlin.jvm")
}

description = "Benchmark comparison and test duration checks"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.gson)
    compileOnly(gradleApi())
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:${project.bootstrapKotlinVersion}")
}

gradlePlugin {
    plugins {
        create("benchmarkReport") {
            id = "org.jetbrains.kotlin.benchmarks.report"
            implementationClass = "org.jetbrains.kotlin.benchmarks.report.BenchmarkReportPlugin"
        }
    }
}
