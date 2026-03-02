# OHOS .def File Generator

A Kotlin tool that automatically generates .def files for OHOS and HarmonyOS SDK.

## Features

### Core Features

1. **Automatic header scanning**
   - Scans all `.h` headers under `ohos_include` and `hms_include`
   - Only processes headers that contain `@addtogroup`
   - Ignores system standard headers (e.g. `string.h`, `stdlib.h`)

2. **Extract key information**
   - **Module name**: from `@addtogroup` (required)
   - **Kit name**: from `@kit` (required)
   - **Library name**: from `@library`, used for `linkerOpts`
   - **Dependencies**: derived from `#include` in headers

3. **Generate .def files**
   - Generates compliant .def files from extracted data
   - Builds `package` name (format: `platform.KitName.ModuleName`)
   - Builds `headerFilter` (single dir: wildcard; multiple dirs: list files)
   - Extracts and generates `linkerOpts` from `@library`
   - Defaults: `language = C++`, `compilerOpts = -std=c++17`, `enableUndefinedApiProtection = true`

4. **Dependency validation**
   - **Circular dependency detection**: reports cycles between modules
   - **Missing dependency detection**: reports missing dependent modules
   - **Config completeness**: checks all required fields
   - **headerFilter size**: warns when header count exceeds threshold

5. **Logging**
   - One log file per run (with timestamp)
   - Per-step details
   - Statistics (files scanned, modules generated, etc.)
   - Reports issues that need manual handling

## Directory structure

```
ohos-def-generator/
├── build.gradle.kts                   # Gradle build config
├── build_ohos_def.sh                  # Entry script
├── def-generator.config               # Central config (paths, SDK type, language/compiler opts)
├── README.md                          # This document
├── .gitignore                         # Git ignore
├── sysroot/                           # SDK source dir (default)
│   ├── README.md                      # sysroot usage
│   ├── ohos_include/                  # OHOS SDK headers
│   └── hms_include/                   # HarmonyOS SDK headers
├── output/                            # Output dir (default)
│   ├── *.def                          # Generated .def files
│   └── logs/                          # Log files
└── src/main/kotlin/org/jetbrains/kotlin/native/defgen/
    ├── Main.kt                        # Entry and CLI parsing
    ├── Models.kt                      # Data models
    ├── Logger.kt                      # Logging
    ├── HeaderScanner.kt               # Header scanner
    ├── DefFileGenerator.kt            # .def file generator
    └── DependencyChecker.kt           # Dependency checker
```

## Quick start

### 1. Prepare SDK headers

Copy SDK headers into `sysroot`:

```bash
# Go to tool directory
cd kotlin-native/tools/ohos-def-generator

# Copy OHOS SDK headers
cp -r /path/to/ohos/sdk/include/* ./sysroot/ohos_include/

# Copy HarmonyOS SDK headers (optional)
cp -r /path/to/harmony/sdk/include/* ./sysroot/hms_include/
```

### 2. Run the tool

```bash
# Default config (read from sysroot, write to output)
./build_ohos_def.sh

# Use Konan sysroot (~/.konan/dependencies/), no extra copy
# Set DEFAULT_SOURCE_REL="konan" in def-generator.config or:
USE_KONAN_SYSROOT=1 ./build_ohos_def.sh

# OHOS SDK only
./build_ohos_def.sh --sdk ohos

# HMS SDK only
./build_ohos_def.sh --sdk hms

# Help
./build_ohos_def.sh --help
```

### 3. Results

Generated .def files are written to the `output` directory.

## Configuration

Default behavior is controlled by **`def-generator.config`**. Edit it to change paths, SDK type, language, and compiler options without changing the script.

| Option | Description | Example |
|--------|--------------|---------|
| `DEFAULT_SOURCE_REL` | Default source dir (relative to script); use `konan` to use Konan sysroot | `sysroot` / `konan` |
| `DEFAULT_OUTPUT_REL` | Default output dir | `output` |
| `DEFAULT_SDK` | Default SDK type | `ohos` / `hms` / `all` |
| `LANGUAGE` | def `language` field | `C++` |
| `COMPILER_OPTS` | def `compilerOpts` field | `-std=c++17` |
| `JAR_NAME` | Output jar name | `ohos-def-generator-1.0.0.jar` |

CLI options `--source` / `--output` / `--sdk` override these defaults.

## Usage

### Option 1: Script (recommended)

```bash
# Default (sysroot -> output)
./build_ohos_def.sh

# Custom source and output
./build_ohos_def.sh --source /path/to/sdk --output /path/to/output

# OHOS SDK only
./build_ohos_def.sh --sdk ohos

# HMS SDK only
./build_ohos_def.sh --sdk hms
```

### Option 2: Gradle directly

```bash
# Build
./gradlew :kotlin-native:tools:ohos-def-generator:build

# Run
./gradlew :kotlin-native:tools:ohos-def-generator:run --args="--source /path/to/sdk --output /path/to/output"
```

## Command-line options

| Option | Description | Default |
|--------|-------------|---------|
| `--source <path>` | SDK source directory | `./sysroot` |
| `--output <path>` | Output directory | `./output` |
| `--sdk <type>` | SDK type: ohos, hms, all | `all` |
| `--language <lang>` | Language | `C++` |
| `--compiler-opts <opts>` | Compiler options | `-std=c++17` |
| `--help, -h` | Show help | - |

## Fixed config

In `build_ohos_def.sh` these are fixed:

```bash
LANGUAGE="C++"
COMPILER_OPTS="-std=c++17"
```

All generated .def files include:
- `language = C++`
- `compilerOpts = -std=c++17`
- `enableUndefinedApiProtection = true`

## Generated .def format

```properties
# from harmonysdk  (HMS SDK only)
package = platform.AVCodecKit.AVCapability
headers = multimedia/player_framework/native_avcapability.h
headerFilter = multimedia/player_framework/native_avcapability.h
depends = CodecBase Core
linkerOpts = -lnative_media_codecbase
language = C++
compilerOpts = -std=c++17
enableUndefinedApiProtection = true
```

## Workflow

1. **Step 1: Scan headers**
   - Scan `ohos_include` and/or `hms_include`
   - Filter headers with `@addtogroup`
   - Extract module name, Kit name, library name, dependencies

2. **Step 2: Build module list**
   - Group headers by module
   - Build header-to-module map
   - Resolve module dependencies

3. **Step 3: Generate .def files**
   - Create one .def per module
   - Build package, headers, headerFilter, etc.
   - Apply fixed config

4. **Step 4: Validate .def files**
   - Detect circular dependencies
   - Check missing dependencies
   - Validate config completeness
   - Check headerFilter size

## Log output

Each run creates a log under `<output>/logs/`:

```
logs/ohos_def_generator_20260206_123456.log
```

Logs include:
- Per-step details
- Headers and modules found
- Statistics (files scanned, modules generated, etc.)
- Validation results and issues to fix manually

## Sample output

```
========================================
  OHOS .def file generator
========================================

▶️  Step 1: Scan header files
✅ Step 1: Scan header files done (2543ms)

▶️  Step 2: Build module list
✅ Step 2: Build module list done (125ms)

▶️  Step 3: Generate .def files
✅ Generated 156 .def files to: /path/to/output
✅ Step 3: Generate .def files done (342ms)

▶️  Step 4: Validate .def files
✅ All validations passed!
✅ Step 4: Validate .def files done (89ms)

📊 Statistics:
  Total headers scanned: 1980
  Valid headers found: 856
    - OHOS headers: 795
    - HMS headers: 61
  Modules generated: 156
  .def files generated: 156
  Total duration: 3099ms

📝 Log file: /path/to/output/logs/ohos_def_generator_20260206_123456.log

✅ All .def files generated successfully!
```

## Handling validation issues

### Circular dependencies

If circular dependencies are found, the log shows:

```
❌ Found 2 circular dependencies:
  Circular: ModuleA -> ModuleB -> ModuleC -> ModuleA
  Circular: ModuleX -> ModuleY -> ModuleX
```

**Fix**: Edit .def files to remove or adjust dependencies.

### Missing dependencies

If dependencies are missing:

```
❌ Found 3 modules with missing dependencies:
  Module 'AVCapability' missing deps: SomeModule, AnotherModule
```

**Fix**:
1. Check that dependent modules are generated
2. If external, add them manually to the .def
3. If not needed, the warning can be ignored

### Large headerFilter

If header count exceeds the threshold (default 10):

```
⚠️  Found 1 module with oversized headerFilter:
  Module 'LargeModule' has 25 headers
```

**Suggestion**: Consider splitting into multiple modules.

### Incomplete config

If required fields are missing:

```
❌ Found 1 incomplete config:
  Module 'IncompleteModule' has incomplete config: missing Kit info
```

**Fix**: Edit the .def and add the missing fields.

## Header filtering

### Included
- Must contain `@addtogroup`
- Must have `.h` extension
- Under `ohos_include` or `hms_include`

### Ignored
- System headers: `string.h`, `stdlib.h`, `stdio.h`, `stdint.h`, `stdbool.h`, `stddef.h`, `math.h`, `time.h`, `errno.h`, `limits.h`, `float.h`, `assert.h`, `setjmp.h`, `signal.h`, `stdarg.h`, `pthread.h`, `unistd.h`, `fcntl.h`

## Notes

1. **First run**: The script may build the project on first run (can take a few minutes).
2. **Errors**: If a header is missing `@kit`, the tool warns but continues.
3. **Dependencies**: The tool infers dependencies; manual tweaks may be needed.
4. **Circular deps**: Resolve by editing .def files.
5. **Incremental**: The tool regenerates all .def files each run; back up any manually edited files.

## Troubleshooting

### gradlew not found
```
❌ Error: Cannot run. Please install Gradle or ensure an executable gradlew exists at repo root, then run this script.
```
**Fix**: From repo root run `./gradlew :kotlin-native:tools:ohos-def-generator:build`

### Source directory does not exist
```
❌ Error: Source directory does not exist: /path/to/source
```
**Fix**: Check the path passed to `--source`

### No valid headers found
```
❌ No valid header files found
```
**Fix**:
1. Ensure source has `ohos_include` or `hms_include` subdirs
2. Ensure headers contain `@addtogroup`

## Developer info

- **Path**: `kotlin-native/tools/ohos-def-generator`
- **Main class**: `org.jetbrains.kotlin.native.defgen.MainKt`
- **Build**: Gradle 7.x+
- **Kotlin**: 1.9+

## Customization

To customize or extend:

- **HeaderScanner.kt**: Header scanning and parsing
- **DefFileGenerator.kt**: .def generation rules
- **DependencyChecker.kt**: Validation rules and thresholds
- **Main.kt**: New CLI options

## License

This tool is part of the Kotlin/Native project and follows the project’s open source license.
