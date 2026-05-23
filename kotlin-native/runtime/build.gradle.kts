/*
 * Copyright 2010-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */
import org.jetbrains.kotlin.ExecClang
import org.jetbrains.kotlin.PlatformInfo
import org.jetbrains.kotlin.bitcode.CompileToBitcodeExtension
import org.jetbrains.kotlin.cpp.CppUsage
import org.jetbrains.kotlin.dependencies.NativeDependenciesExtension
import org.jetbrains.kotlin.gradle.plugin.konan.tasks.KonanCacheTask
import org.jetbrains.kotlin.gradle.plugin.konan.tasks.KonanCompileTask
import org.jetbrains.kotlin.konan.target.*
import org.jetbrains.kotlin.library.KOTLIN_NATIVE_STDLIB_NAME
import org.jetbrains.kotlin.nativeDistribution.nativeDistribution
import org.jetbrains.kotlin.platformManager
import org.jetbrains.kotlin.testing.native.GitDownloadTask
import org.gradle.kotlin.dsl.support.serviceOf
import org.gradle.process.ExecOperations
import java.net.URI
import org.jetbrains.kotlin.konan.target.Architecture as TargetArchitecture

val kotlinVersion: String by rootProject.extra

plugins {
    id("base")
    id("compile-to-bitcode")
    id("runtime-testing")
}

if (HostManager.host == KonanTarget.MACOS_ARM64) {
    project.configureJvmToolchain(JdkMajorVersion.JDK_17_0)
}

val breakpadRepo = providers.gradleProperty("breakpadGitRepo")
        .orElse(providers.environmentVariable("BREAKPAD_GIT_REPO"))
        .getOrElse("https://github.com/google/breakpad.git")

val breakpadRevision = providers.gradleProperty("breakpadGitRevision")
        .orElse(providers.environmentVariable("BREAKPAD_GIT_REVISION"))
        .getOrElse("v2024.02.16")

val downloadBreakpad = tasks.register<GitDownloadTask>("downloadBreakpad") {
    description = "Retrieves Breakpad sources"
    repository.set(URI.create(breakpadRepo))
    revision.set(breakpadRevision)
    outputDirectory.set(layout.buildDirectory.dir("breakpad"))
}

val breakpadLocation = downloadBreakpad.flatMap { it.outputDirectory }

val breakpadSources by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named("sources-directory"))
        attribute(ArtifactTypeDefinition.ARTIFACT_TYPE_ATTRIBUTE, ArtifactTypeDefinition.DIRECTORY_TYPE)
    }
}

artifacts {
    add(breakpadSources.name, breakpadLocation)
}

googletest {
    revision = project.property("gtestRevision") as String
    refresh = project.hasProperty("refresh-gtest")
}

val targetList = enabledTargets(extensions.getByType<PlatformManager>())

// Per-target toggle for the precise-stackmap path. Rationale:
//   - x86_64 and x86_32 are unsupported by the precise stackmap pipeline (the
//     runtime needs fp-based FpUnwind, OHOS arm64 TBI for KNStateWord bit 59,
//     arm64 asm trampolines, and fixed-size arm64 insn stackmap encoding), so
//     they default OFF.
//   - ARM32 is OFF for the same architectural reason (Thumb mixed-size insn
//     encoding breaks fixed-size stackmap; lacks arm64 asm trampolines).
//   - Of the arm64 targets the pipeline supports, only ohos_arm64 defaults ON
//     to match the bundled CRT layer (ohos_arm64 is the single target with a
//     matching libcrt.so). Every other arm64 target defaults OFF (conservative
//     /shadow-stack baseline) but can be flipped ON independently of CRT — see
//     resolveEnableCrt below for the STACKMAP / CRT split.
//
// Resulting one-dist layout (only ohos_arm64 is ON by default):
//   dist/konan/targets/ohos_arm64/native/runtime.bc   — ENABLE_STACKMAP=1
//   dist/konan/targets/macos_arm64/native/runtime.bc  — ENABLE_STACKMAP=0
//   dist/konan/targets/linux_x64/native/runtime.bc    — ENABLE_STACKMAP=0
//   dist/konan/targets/macos_x64/native/runtime.bc    — ENABLE_STACKMAP=0
//
// User app build picks the matching codegen behaviour via
// KonanConfig.enableStackmap (also target-aware default: ohos_arm64 only), so
// users do not need to pass `-Xbinary=enableStackmap=...` for the common case.
//
// Override priority (highest to lowest):
//   1. -Pkotlin.native.precise.stackmap.<target_name>=true|false  (per-target)
//   2. -Pkotlin.native.precise.stackmap=true|false                (global)
//   3. Default: only ohos_arm64
fun resolveEnableStackmap(target: KonanTarget): Boolean {
    val perTarget = project.findProperty("kotlin.native.precise.stackmap.${target.name}") as String?
    if (perTarget != null) return perTarget.toBoolean()
    val global = project.findProperty("kotlin.native.precise.stackmap") as String?
    if (global != null) return global.toBoolean()
    return target == KonanTarget.OHOS_ARM64
}

// CRT requires STACKMAP; STACKMAP can stand alone. STACKMAP=false + explicit
// CRT=true fails loudly. Override via -Pkotlin.native.crt[.<target>].
fun resolveEnableCrt(target: KonanTarget): Boolean {
    val stackmap = resolveEnableStackmap(target)
    val perTarget = project.findProperty("kotlin.native.crt.${target.name}") as String?
    val global = project.findProperty("kotlin.native.crt") as String?
    val explicit = perTarget?.toBoolean() ?: global?.toBoolean()
    if (explicit == true && !stackmap) {
        error("kotlin.native.crt=true is incompatible with kotlin.native.precise.stackmap=false on target ${target.name}: CRT requires the precise-stackmap pipeline")
    }
    return explicit ?: stackmap
}

fun CompileToBitcodeExtension.Module.enablePreciseStackmapAndCrt(target: KonanTarget) {
    if (resolveEnableStackmap(target)) compilerArgs.add("-DENABLE_STACKMAP=1")
    if (resolveEnableCrt(target)) compilerArgs.add("-DENABLE_CRT=1")
}

// stdlib klib is a single artifact whose manifest covers all targets (see
// stdlibBuildTask in §Stdlib region below). For v1 we honour the GLOBAL property
// here without per-target override. Default = true preserves the original ON
// behaviour; user app codegen handles per-target stub vs non-stub dispatch.
val globalStackmapFlagForStdlib = (project.findProperty("kotlin.native.precise.stackmap") as String?)?.toBoolean() ?: true

// NOTE: the list of modules is duplicated in `RuntimeModule.kt`
bitcode {
    allTargets {
        module("main") {
            headersDirs.from(files(
                "src/externalCallsChecker/common/cpp",
                "src/objcExport/cpp",
                "src/breakpad/cpp",
                "src/crashHandler/common/cpp",
                "src/gc/crt/cpp",
                "src/alloc/crt/cpp",
                "src/mm/cpp",
                "src/alloc/common/cpp",
                "src/gcScheduler/common/cpp",
                "src/gc/common/cpp",
                "src/main/cpp",
                "../../third-party/common-rt",
                "../../third-party/common-rt/common_interfaces",
                "../../third-party/common-rt/common_components",
                "../../third-party/common-rt/libpandabase",
                "../../third-party/common-rt/libpandabase/utils",
                "../../third-party/common-rt/third_party_bounds_checking_function/include"
            ))
            sourceSets {
                main {
                    // When -Pkotlin.native.runtime.excludeNapi=true, exclude NapiInterface.cpp
                    if (project.findProperty("kotlin.native.runtime.excludeNapi") == "true") {
                        inputFiles.exclude("NapiInterface.cpp")
                    }
                    // TODO: Split out out `base` module and merge it together with `main` into `runtime.bc`
                    if (sanitizer == null) {
                        outputFile.set(layout.buildDirectory.file("bitcode/main/$target/runtime.bc"))
                    }
                    // Fix Gradle Configuration Cache: this task depends on headers from breakpad; support this task being configured
                    // before breakpad is actually downloaded.
                    compileTask.configure {
                        dependsOn(downloadBreakpad)
                    }
                }
                testFixtures {
                    // Fix Gradle Configuration Cache: this task depends on headers from breakpad; support this task being configured
                    // before breakpad is actually downloaded.
                    compileTask.configure {
                        dependsOn(downloadBreakpad)
                    }
                }
                test {
                    // Fix Gradle Configuration Cache: this task depends on headers from breakpad; support this task being configured
                    // before breakpad is actually downloaded.
                    compileTask.configure {
                        dependsOn(downloadBreakpad)
                    }
                }
            }
            // Memory.cpp / Memory.h / Natives.cpp are guarded by #ifdef ENABLE_STACKMAP.
            enablePreciseStackmapAndCrt(target)
        }

        testsGroup("main_test") {
            testedModules.addAll("main")
            // TODO(KT-53776): Some tests depend on allocator being legacy.
            testSupportModules.addAll("mm", "noop_externalCallsChecker", "common_alloc", "legacy_alloc", "std_alloc", "common_gc", "noop_gc", "common_gcScheduler", "manual_gcScheduler", "objc", "noop_crashHandler")
        }

        // Headers from here get reused by Swift Export, so this module should not depend on anything in the runtime
        module("objcExport") {
            enablePreciseStackmapAndCrt(target)
            // There must not be any implementation files, only headers.
            sourceSets {}
        }

        module("breakpad") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(breakpadLocation)
            val sources = listOf(
                    "client/mac/crash_generation/crash_generation_client.cc",
                    "client/mac/handler/breakpad_nlist_64.cc",
                    "client/mac/handler/dynamic_images.cc",
                    "client/mac/handler/exception_handler.cc",
                    "client/mac/handler/minidump_generator.cc",
                    "client/mac/handler/protected_memory_allocator.cc",
                    "client/minidump_file_writer.cc",
                    "common/mac/MachIPC.mm",
                    "common/mac/arch_utilities.cc",
                    "common/mac/file_id.cc",
                    "common/mac/macho_id.cc",
                    "common/mac/macho_utilities.cc",
                    "common/mac/macho_walker.cc",
                    "common/mac/string_utilities.cc",
                    "common/mac/bootstrap_compat.cc",
                    "common/convert_UTF.cc",
                    "common/md5.cc",
                    "common/string_conversion.cc",
            )
            sourceSets {
                main {
                    inputFiles.from(srcRoot.dir("src"))
                    inputFiles.setIncludes(sources)
                    headersDirs.setFrom(srcRoot.dir("src"), project.layout.projectDirectory.dir("src/breakpad/cpp"))
                    // Fix Gradle Configuration Cache: support this task being configured before breakpad sources are actually downloaded.
                    compileTask.configure {
                        inputFiles.setFrom(sources.map { breakpadLocation.get().dir("src").file(it) })
                        dependsOn(downloadBreakpad)
                    }
                }
            }

            compilerArgs.set(listOf(
                    "-std=c++17",
                    "-DHAVE_MACH_O_NLIST_H",
                    "-DHAVE_CONFIG_H",
            ))

            onlyIf { it.family == Family.OSX }
        }

        module("libbacktrace") {
            enablePreciseStackmapAndCrt(target)
            val elfSize = when (target.architecture) {
                TargetArchitecture.X64, TargetArchitecture.ARM64 -> 64
                TargetArchitecture.X86, TargetArchitecture.ARM32 -> 32
                else -> 32 // TODO(KT-66500): remove after the bootstrap
            }
            val useMachO = target.family.isAppleFamily
            val useElf = target.family in listOf(Family.LINUX, Family.ANDROID, Family.OHOS)

            sourceSets {
                main {
                    inputFiles.from(srcRoot.dir("c"))
                    inputFiles.include(listOfNotNull(
                            "atomic.c",
                            "backtrace.c",
                            "dwarf.c",
                            "elf.c".takeIf { useElf },
                            "fileline.c",
                            "macho.c".takeIf { useMachO },
                            "mmap.c",
                            "mmapio.c",
                            "posix.c",
                            "print.c",
                            "simple.c",
                            "sort.c",
                            "state.c"
                    ))
                    headersDirs.setFrom(srcRoot.dir("c/include"))
                }
            }

            compiler.set("clang")
            compilerArgs.set(listOfNotNull(
                    "-std=gnu11",
                    "-funwind-tables",
                    "-Werror",
                    "-W",
                    "-Wall",
                    "-Wwrite-strings",
                    "-Wstrict-prototypes",
                    "-Wmissing-prototypes",
                    "-Wold-style-definition",
                    "-Wmissing-format-attribute",
                    "-Wcast-qual",
                    "-O2",
                    "-DBACKTRACE_ELF_SIZE=$elfSize".takeIf { useElf },
                    "-Wno-atomic-alignment"
            ))

            onlyIf { it.supportsLibBacktrace() }
        }

        module("compiler_interface") {
            headersDirs.from(files("src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp"))
            sourceSets {
                main {}
            }
            // KONAN_COMPILER_INTERFACE is enabled together with ENABLE_STACKMAP so the
            // compiler-interface module stays consistent with the stackmap build flavour.
            if (resolveEnableStackmap(target)) compilerArgs.add("-DKONAN_COMPILER_INTERFACE=1")
            enablePreciseStackmapAndCrt(target)
        }

        module("launcher") {
            enablePreciseStackmapAndCrt(target)
            headersDirs.from(files("src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp"))
            sourceSets {
                main {}
            }
        }

        module("crt") {
            enablePreciseStackmapAndCrt(target)
            val crtEnabled = resolveEnableCrt(target)
            onlyIf { crtEnabled }
            srcRoot.set(layout.projectDirectory.dir("src/crt"))
            headersDirs.from(files(
                    "src/alloc/common/cpp",
                    "src/gcScheduler/common/cpp",
                    "src/gc/common/cpp",
                    "src/mm/cpp",
                    "src/externalCallsChecker/common/cpp",
                    "src/objcExport/cpp",
                    "src/main/cpp",
                    "src",
                    "../../third-party/common-rt",
                    "../../third-party/common-rt/common_interfaces",
                    "../../third-party/common-rt/libpandabase",
                    "../../third-party/common-rt/third_party_bounds_checking_function/include"
            ))
            sourceSets {
                main {}
            }
        }

        module("debug") {
            enablePreciseStackmapAndCrt(target)
            headersDirs.from(files("src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp"))
            sourceSets {
                main {}
            }
        }

        module("common_alloc") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(layout.projectDirectory.dir("src/alloc/common"))
            headersDirs.from(files(
                "src/gcScheduler/common/cpp",
                "src/gc/common/cpp",
                "src/mm/cpp",
                "src/externalCallsChecker/common/cpp",
                "src/objcExport/cpp",
                "src/main/cpp",
                "../../third-party/common-rt",
                "../../third-party/common-rt/common_interfaces",
                "../../third-party/common-rt/common_components",
                "../../third-party/common-rt/libpandabase",
                "../../third-party/common-rt/libpandabase/utils",
                "../../third-party/common-rt/third_party_bounds_checking_function/include"
            ))
            sourceSets {
                main {}
                test {}
            }
        }

        testsGroup("common_alloc_test") {
            testedModules.addAll("common_alloc")
            testSupportModules.addAll("main", "mm", "noop_externalCallsChecker", "custom_alloc", "common_gc", "noop_gc", "common_gcScheduler", "manual_gcScheduler", "objc", "noop_crashHandler")
        }

        module("std_alloc") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(layout.projectDirectory.dir("src/alloc/std"))
            headersDirs.from(files(
                "src/alloc/common/cpp",
                "src/alloc/legacy/cpp",
                "src/gcScheduler/common/cpp",
                "src/gc/common/cpp",
                "src/mm/cpp",
                "src/externalCallsChecker/common/cpp",
                "src/objcExport/cpp",
                "src/main/cpp",
                "../../third-party/common-rt",
                "../../third-party/common-rt/common_interfaces",
                "../../third-party/common-rt/common_components",
                "../../third-party/common-rt/libpandabase",
                "../../third-party/common-rt/libpandabase/utils",
                "../../third-party/common-rt/third_party_bounds_checking_function/include"
            ))
            sourceSets {
                main {}
            }
        }

        module("crt_alloc") {
            enablePreciseStackmapAndCrt(target)
            val crtEnabled = resolveEnableCrt(target)
            onlyIf { crtEnabled }
            srcRoot.set(layout.projectDirectory.dir("src/alloc/crt"))
            headersDirs.from(files(
                "src",
                "src/gc/crt/cpp",
                "src/alloc/common/cpp",
                "src/gcScheduler/common/cpp",
                "src/gc/common/cpp",
                "src/mm/cpp",
                "src/externalCallsChecker/common/cpp",
                "src/objcExport/cpp",
                "src/main/cpp",
                "../../third-party/common-rt",
                "../../third-party/common-rt/common_interfaces",
                "../../third-party/common-rt/common_components",
                "../../third-party/common-rt/libpandabase",
                "../../third-party/common-rt/libpandabase/utils",
                "../../third-party/common-rt/third_party_bounds_checking_function/include"
            ))
            sourceSets {
                main {}
                test {}
                testFixtures {}
            }
        }

        module("custom_alloc") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(layout.projectDirectory.dir("src/alloc/custom"))
            headersDirs.from(files(
                "src/alloc/common/cpp",
                "src/gcScheduler/common/cpp",
                "src/gc/common/cpp",
                "src/mm/cpp",
                "src/externalCallsChecker/common/cpp",
                "src/objcExport/cpp",
                "src/main/cpp",
                "../../third-party/common-rt",
                "../../third-party/common-rt/common_interfaces",
                "../../third-party/common-rt/common_components",
                "../../third-party/common-rt/libpandabase",
                "../../third-party/common-rt/libpandabase/utils",
                "../../third-party/common-rt/third_party_bounds_checking_function/include"
            ))
            compilerArgs.add("-DKOTLIN_NATIVE_HIAPPEVENT_FW_VERSION=$kotlinVersion")
            sourceSets {
                main {}
                test {}
                testFixtures {}
            }
        }

        testsGroup("custom_alloc_test") {
            testedModules.addAll("custom_alloc")
            // TODO(KT-53776): Some tests depend on GC not being noop.
            testSupportModules.addAll("main", "noop_externalCallsChecker", "mm", "common_alloc", "common_gc", "concurrent_ms_gc", "common_gcScheduler", "manual_gcScheduler", "objc", "noop_crashHandler")
        }

        module("legacy_alloc") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(layout.projectDirectory.dir("src/alloc/legacy"))
            headersDirs.from(files(
                "src/alloc/common/cpp",
                "src/gcScheduler/common/cpp",
                "src/gc/common/cpp",
                "src/mm/cpp",
                "src/externalCallsChecker/common/cpp",
                "src/objcExport/cpp",
                "src/main/cpp",
                "../../third-party/common-rt",
                "../../third-party/common-rt/common_interfaces",
                "../../third-party/common-rt/common_components",
                "../../third-party/common-rt/libpandabase",
                "../../third-party/common-rt/libpandabase/utils",
                "../../third-party/common-rt/third_party_bounds_checking_function/include"
            ))
            sourceSets {
                main {}
                test {}
                testFixtures {}
            }
        }

        testsGroup("std_legacy_alloc_test") {
            testedModules.addAll("legacy_alloc")
            testSupportModules.addAll("main", "noop_externalCallsChecker", "mm", "common_alloc", "std_alloc", "common_gc", "noop_gc", "common_gcScheduler", "manual_gcScheduler", "objc", "noop_crashHandler")
        }

        module("exceptionsSupport") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(layout.projectDirectory.dir("src/exceptions_support"))
            headersDirs.from(files("src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp"))
            sourceSets {
                main {}
            }
        }

        module("source_info_core_symbolication") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(layout.projectDirectory.dir("src/source_info/core_symbolication"))
            headersDirs.from(files("src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp"))
            sourceSets {
                main {}
            }

            onlyIf { it.supportsCoreSymbolication() }
        }

        module("source_info_libbacktrace") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(layout.projectDirectory.dir("src/source_info/libbacktrace"))
            headersDirs.from(files("src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp", "src/libbacktrace/c/include"))
            sourceSets {
                main {}
            }

            onlyIf { it.supportsLibBacktrace() }
        }

        module("objc") {
            enablePreciseStackmapAndCrt(target)
            headersDirs.from(files("src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp"))
            sourceSets {
                main {}
            }
        }

        module("test_support") {
            enablePreciseStackmapAndCrt(target)
            headersDirs.from(files(
                    "src/externalCallsChecker/common/cpp",
                    "src/objcExport/cpp",
                    "src/main/cpp",
                    "src",
                    "../../third-party/common-rt",
                    "../../third-party/common-rt/common_interfaces",
                    "../../third-party/common-rt/libpandabase",
                    "../../third-party/common-rt/third_party_bounds_checking_function/include"
            ))
            sourceSets {
                testFixtures {
                    inputFiles.include("**/*.cpp", "**/*.mm")
                }
            }
        }

        module("mm") {
            headersDirs.from(files("src/alloc/common/cpp", "src/gcScheduler/common/cpp", "src/gc/common/cpp", "src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp"))
            sourceSets {
                main {}
                testFixtures {}
                test {}
            }
            // ThreadData.hpp is guarded by #ifdef ENABLE_STACKMAP.
            enablePreciseStackmapAndCrt(target)
        }

        testsGroup("mm_test") {
            testedModules.addAll("mm")
            testSupportModules.addAll("main", "noop_externalCallsChecker", "common_alloc", "custom_alloc", "common_gc", "noop_gc", "common_gcScheduler", "manual_gcScheduler", "objc", "noop_crashHandler")
        }

        module("common_gc") {
            srcRoot.set(layout.projectDirectory.dir("src/gc/common"))
            headersDirs.from(files(
                "src/alloc/common/cpp",
                "src/gcScheduler/common/cpp",
                "src/mm/cpp",
                "src/externalCallsChecker/common/cpp",
                "src/objcExport/cpp",
                "src/main/cpp",
                "../../third-party/common-rt",
                "../../third-party/common-rt/common_interfaces",
                "../../third-party/common-rt/common_components",
                "../../third-party/common-rt/libpandabase",
                "../../third-party/common-rt/libpandabase/utils",
                "../../third-party/common-rt/third_party_bounds_checking_function/include"
            ))
            sourceSets {
                main {}
                test {}
            }
            // MainGCThread.hpp plus the integral stackmap sources (StackMap.cpp/hpp/...)
            // are all guarded by #ifdef ENABLE_STACKMAP.
            enablePreciseStackmapAndCrt(target)
        }

        testsGroup("common_gc_test") {
            testedModules.addAll("common_gc")
            testSupportModules.addAll("main", "mm", "noop_externalCallsChecker", "common_alloc", "custom_alloc", "noop_gc", "common_gcScheduler", "manual_gcScheduler", "objc", "noop_crashHandler")
        }

        module("cmc_gc") {
            enablePreciseStackmapAndCrt(target)
            val crtEnabled = resolveEnableCrt(target)
            onlyIf { crtEnabled }
            srcRoot.set(layout.projectDirectory.dir("src/gc/crt"))
            headersDirs.from(files(
                "src",
                "src/alloc/crt/cpp",
                "src/alloc/common/cpp",
                "src/gcScheduler/common/cpp",
                "src/gc/common/cpp",
                "src/mm/cpp",
                "src/externalCallsChecker/common/cpp",
                "src/objcExport/cpp",
                "src/main/cpp",
                "../../third-party/common-rt",
                "../../third-party/common-rt/common_interfaces",
                "../../third-party/common-rt/common_components",
                "../../third-party/common-rt/libpandabase",
                "../../third-party/common-rt/libpandabase/utils",
                "../../third-party/common-rt/third_party_bounds_checking_function/include"
            ))
            sourceSets {
                main {}
            }
        }

        module("noop_gc") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(layout.projectDirectory.dir("src/gc/noop"))
            headersDirs.from(files("src/alloc/common/cpp", "src/gcScheduler/common/cpp", "src/gc/common/cpp", "src/mm/cpp", "src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp"))
            sourceSets {
                main {}
            }
        }

        module("same_thread_ms_gc") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(layout.projectDirectory.dir("src/gc/stms"))
            headersDirs.from(files("src/alloc/common/cpp", "src/gcScheduler/common/cpp", "src/gc/common/cpp", "src/mm/cpp", "src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp"))
            sourceSets {
                main {}
                test {}
            }
        }

        testsGroup("stms_gc_test") {
            testedModules.addAll("same_thread_ms_gc")
            testSupportModules.addAll("main", "mm", "noop_externalCallsChecker", "common_alloc", "common_alloc", "legacy_alloc", "std_alloc", "common_gc", "common_gcScheduler", "manual_gcScheduler", "objc", "noop_crashHandler")
        }

        testsGroup("stms_gc_custom_test") {
            testedModules.addAll("same_thread_ms_gc")
            testSupportModules.addAll("main", "mm", "noop_externalCallsChecker", "common_alloc", "custom_alloc", "common_gc", "common_gcScheduler", "manual_gcScheduler", "objc", "noop_crashHandler")
        }

        module("pmcs_gc") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(layout.projectDirectory.dir("src/gc/pmcs"))
            headersDirs.from(files("src/alloc/common/cpp", "src/gcScheduler/common/cpp", "src/gc/common/cpp", "src/mm/cpp", "src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp"))
            sourceSets {
                main {}
                testFixtures {}
                test {}
            }
        }

        testsGroup("pmcs_gc_test") {
            testedModules.addAll("pmcs_gc")
            testSupportModules.addAll("main", "mm", "noop_externalCallsChecker", "common_alloc", "common_alloc", "legacy_alloc", "std_alloc", "common_gc", "common_gcScheduler", "manual_gcScheduler", "objc", "noop_crashHandler")
        }

        testsGroup("pmcs_gc_custom_test") {
            testedModules.addAll("pmcs_gc")
            testSupportModules.addAll("main", "mm", "noop_externalCallsChecker", "common_alloc", "custom_alloc", "common_gc", "common_gcScheduler", "manual_gcScheduler", "objc", "noop_crashHandler")
        }

        module("concurrent_ms_gc") {
            srcRoot.set(layout.projectDirectory.dir("src/gc/cms"))
            headersDirs.from(files("src/alloc/common/cpp", "src/gcScheduler/common/cpp", "src/gc/common/cpp", "src/mm/cpp", "src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp"))
            // ConcurrentMark.cpp is guarded by #ifdef ENABLE_STACKMAP.
            enablePreciseStackmapAndCrt(target)
            sourceSets {
                main {}
                test {}
            }
        }

        testsGroup("cms_gc_test") {
            testedModules.addAll("concurrent_ms_gc")
            testSupportModules.addAll("main", "mm", "noop_externalCallsChecker", "common_alloc", "common_alloc", "legacy_alloc", "std_alloc", "common_gc", "common_gcScheduler", "manual_gcScheduler", "objc", "noop_crashHandler")
        }

        testsGroup("cms_gc_custom_test") {
            testedModules.addAll("concurrent_ms_gc")
            testSupportModules.addAll("main", "mm", "noop_externalCallsChecker", "common_alloc", "custom_alloc", "common_gc", "common_gcScheduler", "manual_gcScheduler", "objc", "noop_crashHandler")
        }

        module("common_gcScheduler") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(layout.projectDirectory.dir("src/gcScheduler/common"))
            headersDirs.from(files("src/alloc/common/cpp", "src/gc/common/cpp", "src/mm/cpp", "src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp"))
            sourceSets {
                main {}
                test {}
            }
        }

        testsGroup("common_gcScheduler_test") {
            testedModules.addAll("common_gcScheduler")
            testSupportModules.addAll("main", "mm", "noop_externalCallsChecker", "common_alloc", "custom_alloc", "common_gc", "noop_gc", "manual_gcScheduler", "objc", "noop_crashHandler")
        }

        module("manual_gcScheduler") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(layout.projectDirectory.dir("src/gcScheduler/manual"))
            headersDirs.from(files("src/alloc/common/cpp", "src/gcScheduler/common/cpp", "src/gc/common/cpp", "src/mm/cpp", "src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp"))
            sourceSets {
                main {}
            }
        }

        module("adaptive_gcScheduler") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(layout.projectDirectory.dir("src/gcScheduler/adaptive"))
            headersDirs.from(files("src/alloc/common/cpp", "src/gcScheduler/common/cpp", "src/gc/common/cpp", "src/mm/cpp", "src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp"))
            sourceSets {
                main {}
                test {}
            }
        }

        testsGroup("adaptive_gcScheduler_test") {
            testedModules.addAll("adaptive_gcScheduler")
            testSupportModules.addAll("main", "mm", "noop_externalCallsChecker", "common_alloc", "custom_alloc", "common_gc", "noop_gc", "common_gcScheduler", "objc", "noop_crashHandler")
        }

        module("aggressive_gcScheduler") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(layout.projectDirectory.dir("src/gcScheduler/aggressive"))
            headersDirs.from(files("src/alloc/common/cpp", "src/alloc/crt/cpp", "src/gc/crt/cpp", "src/gcScheduler/common/cpp", "src/gc/common/cpp", "src/mm/cpp", "src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp"))
            sourceSets {
                main {}
                test {}
            }
        }

        testsGroup("aggressive_gcScheduler_test") {
            testedModules.addAll("aggressive_gcScheduler")
            testSupportModules.addAll("main", "mm", "noop_externalCallsChecker", "common_alloc", "custom_alloc", "common_gc", "noop_gc", "common_gcScheduler", "objc", "noop_crashHandler")
        }

        module("impl_externalCallsChecker") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(layout.projectDirectory.dir("src/externalCallsChecker/impl"))
            headersDirs.from("src/alloc/common/cpp", "src/gcScheduler/common/cpp", "src/gc/common/cpp", "src/mm/cpp", "src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp")
            sourceSets {
                main {}
            }
        }

        module("noop_externalCallsChecker") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(layout.projectDirectory.dir("src/externalCallsChecker/noop"))
            headersDirs.from("src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp")
            sourceSets {
                main {}
            }
        }

        module("impl_crashHandler") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(layout.projectDirectory.dir("src/crashHandler/impl"))
            headersDirs.from("src/main/cpp", "src/breakpad/cpp", breakpadLocation.get().dir("src"))
            sourceSets {
                main {
                    // Fix Gradle Configuration Cache: support this task being configured before breakpad sources are actually downloaded.
                    compileTask.configure {
                        dependsOn(downloadBreakpad)
                    }
                }
            }
            onlyIf { it.family == Family.OSX }
        }

        module("noop_crashHandler") {
            enablePreciseStackmapAndCrt(target)
            srcRoot.set(layout.projectDirectory.dir("src/crashHandler/noop"))
            sourceSets {
                main {}
            }
        }

        module("xctest_launcher") {
            enablePreciseStackmapAndCrt(target)
            headersDirs.from(files("src/externalCallsChecker/common/cpp", "src/objcExport/cpp", "src/main/cpp"))

            sourceSets {
                main {}
            }
            onlyIf { it.family.isAppleFamily }
        }
    }
}

val compileStubFiles by tasks.registering {
    description = "Compile stub .s files to object files using Clang"
    group = "kotlin-native"

    // OHOS stubs are AArch64 ELF and host-independent (clang -target aarch64-linux-ohos
    // works from any host). macOS stubs are AArch64 Mach-O assembly: they can only be
    // produced on a MACOS_ARM64 host. The MACOS_X64 host cannot assemble them (the
    // sources are ARM64 instructions, but `-arch x86_64` rejects them) and we have no
    // x86_64 macOS stub sources, so we simply skip the macOS branch on non-ARM64 hosts.
    val canBuildMacosStubs = HostManager.host == KonanTarget.MACOS_ARM64

    val hostTargetName = if (canBuildMacosStubs) "macos_arm64" else null
    val hostClangTarget = if (canBuildMacosStubs) "arm64-apple-macos" else null
    val hostArch = if (canBuildMacosStubs) "arm64" else null

    val arm64OhosStubDirFile = layout.projectDirectory.dir("src/main/cpp/aarch64_linux_ohos_stubs").asFile
    val arm64OhosStubSources = fileTree(arm64OhosStubDirFile) { include("*.s") }
    val arm64OhosOutputDirProvider = layout.buildDirectory.dir("bitcode/main/ohos_arm64/aarch64_linux_ohos_stubs_objs")
    val ohosSources: List<File> = arm64OhosStubSources.files.toList()
    val ohosOutDir: File = arm64OhosOutputDirProvider.get().asFile
    val arm64OhosOutputFiles = ohosSources.map { stubFile ->
        ohosOutDir.resolve(stubFile.name.replace(Regex("\\.s$"), ".o"))
    }

    val arm64MacosStubDirFile = layout.projectDirectory.dir("src/main/cpp/aarch64_macos_stubs").asFile
    val arm64MacosStubSources = if (canBuildMacosStubs) fileTree(arm64MacosStubDirFile) { include("*.s") } else null
    val arm64MacosOutputDirProvider = if (canBuildMacosStubs && hostTargetName != null) {
        layout.buildDirectory.dir("bitcode/main/$hostTargetName/aarch64_macos_stubs_objs")
    } else null
    val macosSources: List<File> = arm64MacosStubSources?.files?.toList().orEmpty()
    val macosOutDir: File? = arm64MacosOutputDirProvider?.get()?.asFile
    val arm64MacosOutputFiles = macosOutDir?.let { dir ->
        macosSources.map { stubFile -> dir.resolve(stubFile.name.replace(Regex("\\.s$"), ".o")) }
    }.orEmpty()

    inputs.files(arm64OhosStubSources)
    arm64MacosStubSources?.let { inputs.files(it) }
    outputs.files(arm64OhosOutputFiles + arm64MacosOutputFiles)

    val platformManager = project.extensions.getByType<PlatformManager>()
    val nativeDependencies = project.extensions.getByType<NativeDependenciesExtension>()
    val execClang = ExecClang.create(project.objects, platformManager)
    val execOps = serviceOf<ExecOperations>()

    dependsOn(nativeDependencies.targetDependency(KonanTarget.OHOS_ARM64))
    if (canBuildMacosStubs) {
        dependsOn(nativeDependencies.llvmDependency)
    }

    doLast {
        ohosOutDir.mkdirs()
        ohosSources.forEach { stubFile ->
            val objFile = ohosOutDir.resolve(stubFile.name.replace(Regex("\\.s$"), ".o"))
            execClang.execToolchainClang(KonanTarget.OHOS_ARM64) {
                executable = "clang"
                args(
                    "-c",
                    stubFile.absolutePath,
                    "-o", objFile.absolutePath,
                    "-target", "aarch64-linux-ohos",
                )
            }
        }

        if (macosOutDir != null && hostClangTarget != null && hostArch != null) {
            macosOutDir.mkdirs()
            macosSources.forEach { stubFile ->
                val objFile = macosOutDir.resolve(stubFile.name.replace(Regex("\\.s$"), ".o"))
                execOps.exec {
                    commandLine(
                        execClang.resolveExecutable("clang"),
                        "-c",
                        stubFile.absolutePath,
                        "-o", objFile.absolutePath,
                        "-target", hostClangTarget,
                        "-arch", hostArch,
                    )
                }
            }
        }
    }
}

// OHOS stubs can be produced from any host; macOS stubs only from Apple hosts (Mach-O assembly).
val nativeProjectDir = project(":kotlin-native").layout.projectDirectory

val copyOhosStubObjsToDist by tasks.registering(Sync::class) {
    description = "Copy compiled OHOS stub .o files into dist/konan/targets/ohos_arm64/stubs_objs/"
    group = "kotlin-native"
    dependsOn(compileStubFiles)
    from(layout.buildDirectory.dir("bitcode/main/ohos_arm64/aarch64_linux_ohos_stubs_objs")) {
        include("*.o")
    }
    into(nativeProjectDir.dir("dist/konan/targets/ohos_arm64/stubs_objs"))
}

val copyMacosStubObjsToDist = if (HostManager.host == KonanTarget.MACOS_ARM64) {
    tasks.register<Sync>("copyMacosStubObjsToDist") {
        description = "Copy compiled macOS stub .o files into dist/konan/targets/macos_arm64/stubs_objs/"
        group = "kotlin-native"
        dependsOn(compileStubFiles)
        from(layout.buildDirectory.dir("bitcode/main/macos_arm64/aarch64_macos_stubs_objs")) {
            include("*.o")
        }
        into(nativeProjectDir.dir("dist/konan/targets/macos_arm64/stubs_objs"))
    }
} else null

val copyStubObjsToDist by tasks.registering {
    description = "Copy compiled stub .o files into dist/konan/targets/<target>/stubs_objs/ (OHOS always; macOS only on Apple Silicon hosts)"
    group = "kotlin-native"
    dependsOn(copyOhosStubObjsToDist)
    copyMacosStubObjsToDist?.let { dependsOn(it) }
}

val objcExportApi by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
    attributes {
        attribute(CppUsage.USAGE_ATTRIBUTE, objects.named(CppUsage.API))
        attribute(ArtifactTypeDefinition.ARTIFACT_TYPE_ATTRIBUTE, ArtifactTypeDefinition.DIRECTORY_TYPE)
    }
}

artifacts {
    // This should be a "public headers" directory and this configuration with artifacts should be defined by
    // CompileToBitcodePlugin itself.
    add(objcExportApi.name, layout.projectDirectory.dir("src/objcExport/cpp"))
}

val runtimeBitcode by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
    attributes {
        attribute(CppUsage.USAGE_ATTRIBUTE, objects.named(CppUsage.LLVM_BITCODE))
    }
}

dependencies {
    runtimeBitcode(project(":kotlin-native:runtime"))
}

targetList.forEach { target ->
    // TODO: replace with a more convenient user-facing task that can build for a specific target.
    //       like compileToBitcode with optional argument --target.
    tasks.register("${target}Runtime") {
        description = "Build all main runtime modules for $target"
        group = CompileToBitcodeExtension.BUILD_TASK_GROUP
        dependsOn(compileStubFiles)
        val dependencies = runtimeBitcode.incoming.artifactView {
            attributes {
                attribute(TargetWithSanitizer.TARGET_ATTRIBUTE, target.withSanitizer())
            }
        }.files
        dependsOn(dependencies)
    }
}

gradle.projectsEvaluated {
    val nativeProject = project(":kotlin-native")
    listOf(
        "dist",
        "distPlatformLibs",
        "bundle",
        "bundleRegular",
        "bundlePrebuilt",
        "crossDist",
        "crossDistRuntime",
        "publishBundlePrebuiltPublicationToMavenRepository",
    ).forEach { taskName ->
        nativeProject.tasks.matching { it.name == taskName }.configureEach {
            dependsOn(compileStubFiles)
            dependsOn(copyStubObjsToDist)
        }
    }
}

val hostRuntime by tasks.registering {
    description = "Build all main runtime modules for host"
    group = CompileToBitcodeExtension.BUILD_TASK_GROUP
    dependsOn("${PlatformInfo.hostName}Runtime")
}

val hostRuntimeTests by tasks.registering {
    description = "Runs all runtime tests for host"
    group = CompileToBitcodeExtension.VERIFICATION_TASK_GROUP
    dependsOn("${PlatformInfo.hostName}RuntimeTests")
}

tasks.named("assemble") {
    dependsOn(targetList.map { "${it}Runtime" })
}

val hostAssemble by tasks.registering {
    dependsOn("${PlatformInfo.hostName}Runtime")
}

tasks.named("clean", Delete::class) {
    this.delete(layout.buildDirectory)
}

// region: Stdlib

val stdlibBuildTask by tasks.registering(KonanCompileTask::class) {
    group = BasePlugin.BUILD_GROUP
    description = "Build the Kotlin/Native standard library"

    // Requires Native distribution with the compiler JARs.
    this.compilerDistribution.set(nativeDistribution)
    dependsOn(":kotlin-native:distCompiler")

    this.outputDirectory.set(
            layout.buildDirectory.dir("stdlib/${HostManager.hostName}/stdlib")
    )

    this.extraOpts.addAll(listOfNotNull(
            "-no-default-libs",
            "-no-endorsed-libs",
            "-nostdlib",
            "-Werror".takeIf { !kotlinBuildProperties.disableWerror },
            "-Xallow-kotlin-package",
            "-Xexplicit-api=strict",
            "-Xexpect-actual-classes",
            "-Xcontext-parameters",
            "-module-name", KOTLIN_NATIVE_STDLIB_NAME,
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlin.contracts.ExperimentalContracts",
            "-opt-in=kotlin.ExperimentalMultiplatform",
            "-opt-in=kotlin.native.internal.InternalForKotlinNative",
            "-language-version",
            "2.2",
            "-api-version",
            "2.2",
            "-Xdont-warn-on-error-suppression",
            "-Xstdlib-compilation",
            "-Xfragment-refines=nativeMain:nativeWasm,nativeMain:common,nativeWasm:common,nativeWasm:commonNonJvm,commonNonJvm:common",
            "-Xmanifest-native-targets=${platformManager.targetValues.joinToString(separator = ",") { it.visibleName }}",
            // stdlibBuildTask is a SINGLE task producing one stdlib klib whose manifest
            // covers all targets (see -Xmanifest-native-targets above), so the klib
            // cannot be per-target. We honour the GLOBAL property here as a v1
            // simplification:
            //   - default (no property): emit stub-suffix calls (klib is "ON-flavoured").
            //   - `-Pkotlin.native.precise.stackmap=false`: emit non-stub calls (klib
            //     is "OFF-flavoured", suitable when ALL targets are OFF).
            // For target-default mode (arm64 ON + x86 OFF in one dist) the rooting still
            // works because the user app's CodeGenerator dispatches stub vs non-stub
            // at app build time based on KonanConfig.enableStackmap (per-target default,
            // see KonanConfig.kt). The pre-baked klib stub references in stdlib are
            // re-lowered against the user app's CodeGenerator config.
            // TODO(per-target-stdlib): refactor stdlibBuildTask to fan out per-target if
            //   the link-time GlobalDCE failure described below resurfaces in x86 builds.
            // Without this, OFF user code links against an ON-built stdlib that
            // pulls in K2RStub.o, which then references `_Kotlin_Any_hashCode`
            // whose `used` attribute (from HAS_SAFEPOINT) is gated out by ENABLE_STACKMAP,
            // so GlobalDCE strips the symbol and ld fails.
            if (!globalStackmapFlagForStdlib) "-Xbinary=enableStackmap=false" else null,
    ))

    val common by sourceSets.creating {
        srcDir(project(":kotlin-stdlib").file("common/src/kotlin"))
        srcDir(project(":kotlin-stdlib").file("common/src/generated"))
        srcDir(project(":kotlin-stdlib").file("unsigned/src"))
        srcDir(project(":kotlin-stdlib").files("src").builtBy(":prepare:build.version:writeStdlibVersion"))
        srcDir(project(":kotlin-test").files("annotations-common/src/main/kotlin"))
        srcDir(project(":kotlin-test").files("common/src/main/kotlin"))
    }

    val commonNonJvm by sourceSets.creating {
        srcDir(project(":kotlin-stdlib").file("common-non-jvm/src"))
    }

    val nativeWasm by sourceSets.creating {
        srcDir(project(":kotlin-stdlib").file("native-wasm/src/"))
    }

    val nativeMain by sourceSets.creating {
        srcDir(project(":kotlin-native:Interop:Runtime").file("src/main/kotlin"))
        srcDir(project(":kotlin-native:Interop:Runtime").file("src/native/kotlin"))
        srcDir(project.file("src/main/kotlin"))
    }
}

val nativeStdlib by tasks.registering(Sync::class) {
    from(stdlibBuildTask)
    into(project.layout.buildDirectory.dir("nativeStdlib"))
}

val cacheableTargetNames = platformManager.hostPlatform.cacheableTargets

cacheableTargetNames.forEach { targetName ->
    tasks.register("${targetName}StdlibCache", KonanCacheTask::class.java) {
        val dist = nativeDistribution

        // Requires Native distribution with stdlib klib and runtime modules for `targetName`.
        this.compilerDistribution.set(dist)
        dependsOn(":kotlin-native:${targetName}CrossDistRuntime")
        // KonanCacheTask invokes konanc -> Linker which links stub .o files
        // (N2KStub/K2NStub/K2RStub/KonanStartStub) on OHOS_ARM64 / MACOS_ARM64 targets.
        // The existing `gradle.projectsEvaluated` hook (~line 685) targets `bundle`/`crossDist`
        // etc. but misses `runtime:${target}StdlibCache`, so stub.o aren't copied to dist
        // before libtool runs. Direct dependsOn here is robust against config cache hits.
        dependsOn(copyStubObjsToDist)
        inputs.dir(dist.map { it.runtime(targetName) }) // manually depend on runtime modules (stdlib cache links these modules in)

        this.klib.fileProvider(nativeStdlib.map { it.destinationDir })
        this.target.set(targetName)
        // This path is used in `:kotlin-native:${targetName}StdlibCache`
        this.outputDirectory.set(layout.buildDirectory.dir("cache/$targetName/$targetName-gSTATIC-system/$KOTLIN_NATIVE_STDLIB_NAME-cache"))
    }
}

// endregion
