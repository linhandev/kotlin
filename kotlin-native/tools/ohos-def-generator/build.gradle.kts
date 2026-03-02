/**
   Eazytec is pleased to support the open source community by making CPF-KMP-CMP available.
   Copyright (C) 2026 Eazytec. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */

plugins {
    kotlin("jvm") version "1.9.20"
    application
}

group = "org.jetbrains.kotlin.native.defgen"
version = "1.0.0"

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.google.code.gson:gson:2.10.1")
    testImplementation(kotlin("test-junit"))
}

application {
    mainClass.set("org.jetbrains.kotlin.native.defgen.MainKt")
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "org.jetbrains.kotlin.native.defgen.MainKt"
        )
    }
    // Pack all dependencies into a fat jar
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

sourceSets {
    main {
        kotlin {
            srcDir("src/main/kotlin")
        }
        resources {
            srcDir("src/main/resources")
        }
    }
    test {
        kotlin {
            srcDir("src/test/kotlin")
        }
    }
}
