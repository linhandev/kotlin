import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":compiler:ir.tree"))

    compileOnly(jpsModel())
    compileOnly(project(":compiler:cli-common"))
    compileOnly(commonDependency("org.jetbrains.intellij.deps:log4j")) { isTransitive = false }

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation(commonDependency("com.fasterxml:aalto-xml")) { isTransitive = false }
    implementation(commonDependency("org.codehaus.woodstox:stax2-api")) { isTransitive = false }
    implementation(libs.intellij.fastutil) { isTransitive = false }
    implementation(intellijJDom())
    implementation(intellijCore())
    implementation(project(":compiler:cli"))
    implementation(project(":compiler:fir:fir-serialization"))
    implementation(project(":compiler:fir:fir-native"))
    implementation(project(":compiler:ir.backend.common"))
    implementation(project(":compiler:ir.backend.native"))
    implementation(project(":compiler:ir.inline"))
    implementation(project(":compiler:ir.objcinterop"))
    implementation(project(":compiler:ir.psi2ir"))
    implementation(project(":compiler:ir.serialization.common"))
    implementation(project(":compiler:ir.serialization.native"))
    implementation(project(":compiler:util"))
    implementation(project(":core:compiler.common.native"))
    implementation(project(":core:descriptors"))
    implementation(project(":kotlin-native:llvmInterop"))
    implementation(project(":kotlin-util-klib"))
    implementation(project(":kotlin-util-klib-metadata"))
    implementation(project(":native:base"))
    implementation(project(":native:frontend.native"))
    implementation(project(":native:kotlin-native-utils"))
    implementation(project(":native:objcexport-header-generator"))
    implementation(project(":native:objcexport-header-generator-k1"))

    testImplementation(libs.junit.jupiter.api)
    testImplementation(testFixtures(project(":native:kotlin-native-utils")))
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(kotlinStdlib())
}

tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions.optIn.addAll(
            listOf(
                    "kotlinx.cinterop.ExperimentalForeignApi",
                    "org.jetbrains.kotlin.backend.konan.InternalKotlinNativeApi",
                    "org.jetbrains.kotlin.ir.symbols.UnsafeDuringIrConstructionAPI"
            )
    )
}

sourceSets {
    "main" { projectDefault() }
    "test" {
        projectDefault()
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

// Drift guard: K2RStubFunctions.kt names must match the `CalleeSavedRegistersStub`
// macro invocations in runtime/src/main/cpp/aarch64_*_stubs/K2RStub.s. Both files
// are hand-edited; this task fails the build on divergence (followups doc task [6]).
//
// Sanity: also asserts the linux_ohos vs macos .s files agree (after stripping
// the Mach-O `_` symbol prefix). They should — the macro definitions are
// effectively identical across both targets.
val verifyK2RStubFunctions by tasks.registering {
    val ohosStubs = rootProject.file("kotlin-native/runtime/src/main/cpp/aarch64_linux_ohos_stubs/K2RStub.s")
    val macosStubs = rootProject.file("kotlin-native/runtime/src/main/cpp/aarch64_macos_stubs/K2RStub.s")
    val k2rStubFunctionsKt = file("src/org/jetbrains/kotlin/backend/konan/llvm/K2RStubFunctions.kt")

    inputs.file(ohosStubs).withPropertyName("ohosStubs")
    inputs.file(macosStubs).withPropertyName("macosStubs")
    inputs.file(k2rStubFunctionsKt).withPropertyName("k2rStubFunctionsKt")
    outputs.file(layout.buildDirectory.file("verifyK2RStubFunctions.ok"))

    doLast {
        // Extract `CalleeSavedRegistersStub <name>` macro invocations.
        val macroRe = Regex("""^\s*CalleeSavedRegistersStub\s+(\w+)\s*$""", RegexOption.MULTILINE)
        fun parseStubsFile(f: java.io.File, stripUnderscore: Boolean): Set<String> =
                macroRe.findAll(f.readText()).map {
                    val n = it.groupValues[1]
                    if (stripUnderscore) n.removePrefix("_") else n
                }.toSortedSet()

        val ohosNames = parseStubsFile(ohosStubs, stripUnderscore = false)
        val macosNames = parseStubsFile(macosStubs, stripUnderscore = true)
        if (ohosNames != macosNames) {
            val onlyOhos = ohosNames - macosNames
            val onlyMacos = macosNames - ohosNames
            throw GradleException(
                    "verifyK2RStubFunctions: linux_ohos and macos K2RStub.s disagree on macro names.\n" +
                            "  Only in linux_ohos: $onlyOhos\n" +
                            "  Only in macos: $onlyMacos")
        }

        // Extract names from K2RStubFunctions.kt's `val names: Set<String> = setOf(...)` block.
        val ktText = k2rStubFunctionsKt.readText()
        val namesStart = ktText.indexOf("val names: Set<String> = setOf(")
        if (namesStart < 0) throw GradleException(
                "verifyK2RStubFunctions: could not locate `val names: Set<String> = setOf(` in K2RStubFunctions.kt")
        val namesEnd = ktText.indexOf(')', startIndex = namesStart)
        val namesBlock = ktText.substring(namesStart, namesEnd)
        val ktNames = Regex(""""([A-Za-z_][A-Za-z0-9_]*)"""").findAll(namesBlock)
                .map { it.groupValues[1] }
                .toSortedSet()

        val missingInKt = ohosNames - ktNames
        val extraInKt = ktNames - ohosNames
        if (missingInKt.isNotEmpty() || extraInKt.isNotEmpty()) {
            throw GradleException(
                    "verifyK2RStubFunctions: K2RStubFunctions.names drift detected.\n" +
                            "  Expected (from K2RStub.s macro invocations): ${ohosNames.size} entries\n" +
                            "  Actual   (in K2RStubFunctions.kt):           ${ktNames.size} entries\n" +
                            "  Missing in .kt (add): $missingInKt\n" +
                            "  Extra in .kt (remove): $extraInKt\n" +
                            "Edit K2RStubFunctions.kt to match K2RStub.s, or vice versa.")
        }

        // linkRootSet special-case: CSafePointSlowPath / CslowPath come from the
        // hand-written SafePointSlowPathStub / slowPathStub blocks (non-macro). Verify
        // both are present in the .s.
        if (!ohosStubs.readText().contains("bl   CSafePointSlowPath") &&
                !ohosStubs.readText().contains("bl CSafePointSlowPath")) {
            throw GradleException(
                    "verifyK2RStubFunctions: expected `bl CSafePointSlowPath` in K2RStub.s " +
                            "(linkRootSet add-on). If renamed/removed, update K2RStubFunctions.linkRootSet.")
        }
        if (!ohosStubs.readText().contains("bl   CslowPath") &&
                !ohosStubs.readText().contains("bl CslowPath")) {
            throw GradleException(
                    "verifyK2RStubFunctions: expected `bl CslowPath` in K2RStub.s " +
                            "(linkRootSet add-on). If renamed/removed, update K2RStubFunctions.linkRootSet.")
        }

        outputs.files.singleFile.writeText("OK ${ohosNames.size} names + linkRootSet add-on(s) verified\n")
    }
}

tasks.named("compileKotlin") {
    dependsOn(verifyK2RStubFunctions)
}

sourcesJar()
javadocJar()
