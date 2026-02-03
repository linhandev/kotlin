/*
 * Copyright 2010-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

import org.jetbrains.kotlin.gradle.plugin.konan.tasks.KonanCacheTask
import org.jetbrains.kotlin.gradle.plugin.konan.tasks.KonanInteropTask
import org.jetbrains.kotlin.PlatformInfo
import org.jetbrains.kotlin.konan.target.*
import org.jetbrains.kotlin.konan.util.*
import org.jetbrains.kotlin.nativeDistribution.nativeDistribution
import org.jetbrains.kotlin.platformLibs.*
import org.jetbrains.kotlin.platformManager
import org.jetbrains.kotlin.utils.capitalized
import java.util.Properties
import java.io.FileInputStream
import java.io.File

plugins {
    id("base")
    id("platform-manager")
}

// region: Util functions.
fun KonanTarget.defFiles() = familyDefFiles(family).map { DefFile(it, this) }

fun defFileToLibName(target: String, name: String) = "$target-$name"

private fun interopTaskName(libName: String, targetName: String) = "compileKonan${libName.capitalized}${targetName.capitalized}"
private fun cacheTaskName(target: String, name: String) = "${defFileToLibName(target, name)}Cache"

// Recursively collect all transitive dependencies
private fun collectAllDependencies(defName: String, target: KonanTarget, visited: MutableSet<String> = mutableSetOf()): Set<String> {
    if (defName in visited) return emptySet()
    visited.add(defName)
    
    val defFile = target.defFiles().find { it.name == defName } ?: return emptySet()
    val allDeps = mutableSetOf<String>()
    
    defFile.config.depends.forEach { depName ->
        allDeps.add(depName)
        allDeps.addAll(collectAllDependencies(depName, target, visited))
    }
    
    return allDeps
}

private abstract class CompilePlatformLibsSemaphore : BuildService<BuildServiceParameters.None>
private abstract class CachePlatformLibsSemaphore : BuildService<BuildServiceParameters.None>

private val compilePlatformLibsSemaphore = gradle.sharedServices.registerIfAbsent("compilePlatformLibsSemaphore", CompilePlatformLibsSemaphore::class.java) {
    if (kotlinBuildProperties.limitPlatformLibsCompilationConcurrency) {
        maxParallelUsages.set(1)
    }
}

private val cachePlatformLibsSemaphore = gradle.sharedServices.registerIfAbsent("cachePlatformLibsSemaphore", CachePlatformLibsSemaphore::class.java) {
    if (kotlinBuildProperties.limitPlatformLibsCacheBuildingConcurrency) {
        maxParallelUsages.set(1)
    }
}

// endregion

if (HostManager.host == KonanTarget.MACOS_ARM64) {
    project.configureJvmToolchain(JdkMajorVersion.JDK_17_0)
}

val cacheableTargetNames = platformManager.hostPlatform.cacheableTargets

val konanPropertiesFile = File(projectDir.parentFile, "konan/konan.properties")
val konanProperties = Properties().apply {
    if (konanPropertiesFile.exists()) {
        FileInputStream(konanPropertiesFile).use { load(it) }
    }
}

val updateDefFileDependenciesTask = tasks.register("updateDefFileDependencies")
val updateDefFileTasksPerFamily = if (HostManager.hostIsMac) {
    registerUpdateDefFileDependenciesForAppleFamiliesTasks(updateDefFileDependenciesTask)
} else {
    emptyMap()
}


enabledTargets(platformManager).forEach { target ->
    val targetName = target.visibleName
    val installTasks = mutableListOf<TaskProvider<out Task>>()
    val cacheTasks = mutableListOf<TaskProvider<out Task>>()

    target.defFiles().forEach { df ->
        val libName = defFileToLibName(targetName, df.name)
        val fileNamePrefix = PlatformLibsInfo.namePrefix
        val artifactName = "${fileNamePrefix}${df.name}"

        val libTask = tasks.register(interopTaskName(libName, targetName), KonanInteropTask::class.java) {
            group = BasePlugin.BUILD_GROUP
            description = "Build the Kotlin/Native platform library '$libName' for '$target'"

            updateDefFileTasksPerFamily[target.family]?.let { dependsOn(it) }

            // Requires Native distribution with compiler JARs and stdlib klib.
            this.compilerDistribution.set(nativeDistribution)
            dependsOn(":kotlin-native:distStdlib")

            this.target.set(targetName)
            this.outputDirectory.set(
                    layout.buildDirectory.dir("konan/libs/$targetName/${fileNamePrefix}${df.name}")
            )
            df.file?.let { this.defFile.set(it) }
            // Collect all transitive dependencies
            val allDependencies = collectAllDependencies(df.name, target)
            allDependencies.forEach { defName ->
                val depTaskName = interopTaskName(defFileToLibName(targetName, defName), targetName)
                val depLibName = defFileToLibName(targetName, defName)
                val depFileNamePrefix = PlatformLibsInfo.namePrefix
                val depArtifactName = "${depFileNamePrefix}${defName}"
                // Use installed klib directory (where cinterop looks for klibs)
                val depInstalledDir = nativeDistribution.map { it.platformLib(name = depArtifactName, target = targetName) }
                this.klibFiles.from(depInstalledDir)
                // Ensure both build and install tasks run first
                dependsOn(tasks.named(depTaskName))
                dependsOn(tasks.named<Sync>(depLibName))
            }
            this.extraOpts.addAll(
                    "-Xpurge-user-libs",
                    "-Xshort-module-name", df.name,
                    "-Xdisable-experimental-annotation",
                    "-no-default-libs",
                    "-no-endorsed-libs",
            )
            if (target.family.isAppleFamily) {
                // Platform Libraries for Apple targets use modules. Use shared cache for them.
                // Keep the path relative to hit the build cache.
                val fmodulesCache = project.layout.buildDirectory.dir("clangModulesCache").get().asFile.toRelativeString(project.layout.projectDirectory.asFile)
                this.extraOpts.addAll("-compiler-option", "-fmodules-cache-path=$fmodulesCache")
            }

            // HarmonyOS only: parse and inject two sysroot (additionalTargetSysRoot) configs for OHOS
            // targets, used for include/lib paths in def files. Omit or remove this block if not using HarmonyOS.
            if (target.family == Family.OHOS) {
                val konanDataDir = System.getenv("KONAN_DATA_DIR")?.let {
                    it.replace("~", System.getProperty("user.home"))
                } ?: "${System.getProperty("user.home")}/.konan"

                val isKbaDef = df.name.startsWith("kba_")

                if (isKbaDef) {
                    // kba_* defs: use kbaTargetSysRoot as the only sysroot by overriding targetSysRoot.
                    val kbaSysrootName = konanProperties.getProperty("kbaTargetSysRoot.ohos")
                    if (kbaSysrootName != null) {
                        val sysrootDir = "$konanDataDir/dependencies/$kbaSysrootName"
                        this.extraOpts.addAll("-compiler-option", "--sysroot=$sysrootDir")
                        this.extraOpts.addAll("-linker-option", "--sysroot=$sysrootDir")
                    }
                } else {
                    // Non-kba defs: keep using additionalTargetSysRoot as extra include/lib search paths.
                    val sysrootName = when (targetName) {
                        "ohos_arm64" -> konanProperties.getProperty("additionalTargetSysRoot.ohos_arm64")
                        "ohos_x64" -> konanProperties.getProperty("additionalTargetSysRoot.ohos_x64")
                        else -> null
                    }

                    if (sysrootName != null) {
                        val includePath = "$konanDataDir/dependencies/$sysrootName/usr/include"
                        val libPath = when (targetName) {
                            "ohos_arm64" -> "$konanDataDir/dependencies/$sysrootName/usr/lib/aarch64-linux-ohos"
                            "ohos_x64" -> "$konanDataDir/dependencies/$sysrootName/usr/lib/x86_64-linux-ohos"
                            else -> null
                        }
                        if (libPath != null) {
                            this.extraOpts.addAll("-compiler-option", "-I$includePath")
                            this.extraOpts.addAll("-linker-option", "-L$libPath")
                        }
                    }
                }
            }
            usesService(compilePlatformLibsSemaphore)
        }

        val klibInstallTask = tasks.register(libName, Sync::class.java) {
            // During the execution of the `:kotlin-native:publish` task, the `:kotlin-native:bundleRegular` subtask
            // traverses the `nativeDistribution` root directory (`kotlin-native/dist/`) to create the bundle.
            // At the same time, the `nativeDistribution` root directory might be populated with `platformLibs` by the
            // `bundlePrebuilt` subtask, which is also triggered by calling `:kotlin-native:publish`.
            //
            // This behavior can result in "Comparison method violates its general contract!" errors because Gradle
            // traverses a **sorted** file list that is being concurrently modified, violating the sorting contract.
            // To prevent this issue, we ensure that the installation of `platformLibs` does not occur while
            // `bundleRegular` is traversing the directory.
            mustRunAfter(":kotlin-native:bundleRegular")

            from(libTask)
            into(nativeDistribution.map { it.platformLib(name = artifactName, target = targetName) })
        }
        installTasks.add(klibInstallTask)

        if (target.name in cacheableTargetNames) {
            val cacheTask = tasks.register(cacheTaskName(targetName, df.name), KonanCacheTask::class.java) {
                val dist = nativeDistribution

                // Requires Native distribution with stdlib klib and its cache for `targetName`.
                this.compilerDistribution.set(dist)
                dependsOn(":kotlin-native:${targetName}CrossDist")
                // Make sure the cache clean-up has happened, so this task can safely write into the shared cache folder
                mustRunAfter(":kotlin-native:distInvalidateStaleCaches")
                inputs.dir(dist.map { it.stdlibCache(targetName) }) // manually depend on the contents of stdlib cache

                // Also, all the depended upon platform libs must have installed their klibs and caches into the native distribution above.
                df.config.depends.forEach { dep ->
                    inputs.dir(tasks.named<KonanCacheTask>(cacheTaskName(targetName, dep)).map { it.outputDirectory })
                    inputs.dir(tasks.named<Sync>(defFileToLibName(targetName, dep)).map { it.destinationDir })
                }

                this.klib.fileProvider(libTask.map { it.outputs.files.singleFile })
                this.target.set(targetName)
                this.outputDirectory.set(dist.map { it.cache(name = artifactName, target = targetName) })

                usesService(cachePlatformLibsSemaphore)
            }
            cacheTasks.add(cacheTask)
        }
    }

    tasks.register("${targetName}Install") {
        dependsOn(installTasks)
    }

    if (target.name in cacheableTargetNames) {
        tasks.register("${targetName}Cache") {
            dependsOn(cacheTasks)

            group = BasePlugin.BUILD_GROUP
            description = "Builds the compilation cache for platform: $targetName"
        }
    }
}

val hostInstall by tasks.registering {
    dependsOn("${PlatformInfo.hostName}Install")
}

val hostCache by tasks.registering {
    dependsOn("${PlatformInfo.hostName}Cache")
}

val cache by tasks.registering {
    dependsOn(tasks.withType(KonanCacheTask::class.java))

    group = BasePlugin.BUILD_GROUP
    description = "Builds all the compilation caches"
}
