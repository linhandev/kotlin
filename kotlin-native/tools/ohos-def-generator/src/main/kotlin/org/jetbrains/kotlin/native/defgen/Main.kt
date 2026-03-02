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

package org.jetbrains.kotlin.native.defgen

import java.nio.file.Path
import kotlin.io.path.*
import kotlin.system.exitProcess

/**
 * Command-line arguments
 */
data class CliArgs(
    val sourceDirs: List<Path>,
    val outputDir: Path,
    val configPath: Path? = null,
    val sdkType: SdkType = SdkType.ALL,
    val language: String = "C++",
    val compilerOpts: String = "-std=c++17"
)

enum class SdkType {
    OHOS, HMS, ALL
}

fun main(args: Array<String>) {
    println("========================================")
    println("  OHOS .def file generator")
    println("========================================\n")
    
    try {
        val cliArgs = parseArgs(args)
        val logDir = cliArgs.outputDir.resolve("logs")
        val logger = Logger(logDir)
        
        logger.info("Starting OHOS .def file generation")
        
        val rules = if (cliArgs.configPath != null && cliArgs.configPath.toFile().exists()) {
            GeneratorConfigLoader.loadRulesFromFile(cliArgs.configPath.toAbsolutePath())
        } else {
            val configDir = cliArgs.sourceDirs.firstOrNull()?.toAbsolutePath()?.parent
            GeneratorConfigLoader.loadRules(configDir)
        }
        
        val stats = Statistics()
        
        // Step 1: Scan header files
        val allHeaderInfos = mutableListOf<HeaderFileInfo>()
        val scanner = HeaderScanner(logger, rules)
        
        logger.step("Step 1: Scan header files") {
            cliArgs.sourceDirs.forEach { sourceDir ->
                val absDir = sourceDir.toAbsolutePath()
                val usrInclude = absDir.resolve("usr/include")
                val isKonanOhos = usrInclude.exists() && absDir.name.contains("sysroot-ohos")
                val isKonanHms = usrInclude.exists() && absDir.name.contains("sysroot-hms")

                if (isKonanOhos) {
                    // Konan OHOS sysroot: scan usr/include only
                    if (cliArgs.sdkType == SdkType.OHOS || cliArgs.sdkType == SdkType.ALL) {
                        val ohosHeaders = scanner.scanHeaders(absDir, isFromHarmonySDK = false, includeSubdir = "usr/include")
                        allHeaderInfos.addAll(ohosHeaders)
                        stats.ohosHeadersScanned += ohosHeaders.size
                    }
                } else if (isKonanHms) {
                    // Konan HMS sysroot: scan usr/include only
                    if (cliArgs.sdkType == SdkType.HMS || cliArgs.sdkType == SdkType.ALL) {
                        val hmsHeaders = scanner.scanHeaders(absDir, isFromHarmonySDK = true, includeSubdir = "usr/include")
                        allHeaderInfos.addAll(hmsHeaders)
                        stats.hmsHeadersScanned += hmsHeaders.size
                    }
                } else {
                    // Default layout: source dir contains ohos_include / hms_include
                    when (cliArgs.sdkType) {
                        SdkType.OHOS, SdkType.ALL -> {
                            val ohosHeaders = scanner.scanHeaders(absDir, isFromHarmonySDK = false)
                            allHeaderInfos.addAll(ohosHeaders)
                            stats.ohosHeadersScanned += ohosHeaders.size
                        }
                        else -> {}
                    }
                    when (cliArgs.sdkType) {
                        SdkType.HMS, SdkType.ALL -> {
                            val hmsHeaders = scanner.scanHeaders(absDir, isFromHarmonySDK = true)
                            allHeaderInfos.addAll(hmsHeaders)
                            stats.hmsHeadersScanned += hmsHeaders.size
                        }
                        else -> {}
                    }
                }
            }
            
            stats.totalHeadersScanned = allHeaderInfos.size + 
                (cliArgs.sourceDirs.sumOf { 
                    val ohosInclude = it.resolve("ohos")
                    val hmsInclude = it.resolve("hms")
                    var count = 0
                    if (ohosInclude.exists()) {
                        count += ohosInclude.toFile().walk().count { f -> f.isFile && f.extension == "h" }
                    }
                    if (hmsInclude.exists()) {
                        count += hmsInclude.toFile().walk().count { f -> f.isFile && f.extension == "h" }
                    }
                    count
                } - allHeaderInfos.size)
            stats.validHeadersFound = allHeaderInfos.size
        }
        
        if (allHeaderInfos.isEmpty()) {
            logger.error("No valid header files found")
            exitProcess(1)
        }
        
        // Step 2: Build module list
        val modules = mutableListOf<ModuleInfo>()
        logger.step("Step 2: Build module list") {
            val headerToModuleMap = scanner.buildHeaderToModuleMap(allHeaderInfos)
            modules.addAll(scanner.aggregateModules(allHeaderInfos, headerToModuleMap))
            stats.modulesGenerated = modules.size
            logger.info("Generated ${modules.size} modules")
        }
        
        // Step 3: Generate .def files
        val configs = mutableListOf<DefConfig>()
        logger.step("Step 3: Generate .def files") {
            val generator = DefFileGenerator(logger, rules, cliArgs.language, cliArgs.compilerOpts)
            configs.addAll(generator.generateDefFiles(modules, cliArgs.outputDir))
            stats.defFilesGenerated = configs.size
        }
        
        // Step 4: Validate .def files
        var validationResult = ValidationResult()
        logger.step("Step 4: Validate .def files") {
            val checker = DependencyChecker(logger, rules)
            validationResult = checker.validate(configs)
        }
        
        stats.endTime = System.currentTimeMillis()
        
        // Output statistics
        logger.logStatistics(stats)
        
        // Output validation result
        logger.logValidationResult(validationResult)
        
        println("\n📝 Log file: ${logger.getLogFilePath()}")
        
        if (validationResult.hasIssues()) {
            println("\n⚠️  There are issues that need manual handling. See the log file for details.")
            exitProcess(1)
        } else {
            println("\n✅ All .def files generated successfully!")
            exitProcess(0)
        }
        
    } catch (e: Exception) {
        System.err.println("❌ Error: ${e.message}")
        e.printStackTrace()
        exitProcess(1)
    }
}

/**
 * Parse command-line arguments
 */
fun parseArgs(args: Array<String>): CliArgs {
    var sourceDirs = mutableListOf<Path>()
    var outputDir: Path? = null
    var configPath: Path? = null
    var sdkType = SdkType.ALL
    var language = "C++"
    var compilerOpts = "-std=c++17"
    
    var i = 0
    while (i < args.size) {
        when (args[i]) {
            "--source" -> {
                if (i + 1 < args.size) {
                    sourceDirs.add(Path(args[++i]))
                } else {
                    throw IllegalArgumentException("--source requires an argument")
                }
            }
            "--output" -> {
                if (i + 1 < args.size) {
                    outputDir = Path(args[++i])
                } else {
                    throw IllegalArgumentException("--output requires an argument")
                }
            }
            "--config" -> {
                if (i + 1 < args.size) {
                    configPath = Path(args[++i])
                } else {
                    throw IllegalArgumentException("--config requires an argument")
                }
            }
            "--sdk" -> {
                if (i + 1 < args.size) {
                    sdkType = when (args[++i].lowercase()) {
                        "ohos" -> SdkType.OHOS
                        "hms" -> SdkType.HMS
                        "all" -> SdkType.ALL
                        else -> throw IllegalArgumentException("--sdk must be ohos, hms or all")
                    }
                } else {
                    throw IllegalArgumentException("--sdk requires an argument")
                }
            }
            "--language" -> {
                if (i + 1 < args.size) {
                    language = args[++i]
                } else {
                    throw IllegalArgumentException("--language requires an argument")
                }
            }
            "--compiler-opts" -> {
                if (i + 1 < args.size) {
                    compilerOpts = args[++i]
                } else {
                    throw IllegalArgumentException("--compiler-opts requires an argument")
                }
            }
            "--help", "-h" -> {
                printUsage()
                exitProcess(0)
            }
            else -> {
                throw IllegalArgumentException("Unknown argument: ${args[i]}")
            }
        }
        i++
    }
    
    // Use defaults
    if (sourceDirs.isEmpty()) {
        // Default: sysroot under tool directory
        val defaultSource = Path("kotlin-native/tools/ohos-def-generator/sysroot").absolute()
        if (defaultSource.exists()) {
            sourceDirs.add(defaultSource)
        } else {
            // If not found, try relative to current working directory
            val relativeSource = Path("sysroot").absolute()
            if (relativeSource.exists()) {
                sourceDirs.add(relativeSource)
            } else {
                throw IllegalArgumentException("--source not specified and default path does not exist: $defaultSource or $relativeSource")
            }
        }
    }
    
    if (outputDir == null) {
        outputDir = Path("kotlin-native/tools/ohos-def-generator/output").absolute()
        // If not found, try relative to current working directory
        if (!outputDir.exists()) {
            val relativeOutput = Path("output").absolute()
            outputDir = relativeOutput
        }
    }
    
    return CliArgs(
        sourceDirs = sourceDirs,
        outputDir = outputDir!!,
        configPath = configPath,
        sdkType = sdkType,
        language = language,
        compilerOpts = compilerOpts
    )
}

/**
 * Print usage
 */
fun printUsage() {
    println("""
        Usage: ohos-def-generator [options]
        
        Options:
          --source <path>       SDK source directory path (may be specified multiple times)
          --output <path>      Output directory path
          --config <path>      Path to rules file def-generator-rules.json (overrides auto lookup)
          --sdk <type>         SDK type: ohos, hms, all (default: all)
          --language <lang>    Programming language (default: C++)
          --compiler-opts <opts>  Compiler options (default: -std=c++17)
          --help, -h           Show this help
        
        Examples:
          # Use default config
          ohos-def-generator
          
          # Specify source and output directories
          ohos-def-generator --source /path/to/sdk --output /path/to/output
          
          # OHOS SDK only
          ohos-def-generator --sdk ohos
    """.trimIndent())
}
