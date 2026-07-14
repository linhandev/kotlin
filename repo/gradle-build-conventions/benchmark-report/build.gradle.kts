plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    id("org.jetbrains.kotlin.jvm")
}

description = "JMH benchmark comparison and test duration checks"

repositories {
    maven(url = "https://redirector.kotlinlang.org/maven/kotlin-dependencies")
    mavenCentral { setUrl("https://cache-redirector.jetbrains.com/maven-central") }
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
