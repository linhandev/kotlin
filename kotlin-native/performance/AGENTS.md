# Kotlin/Native performance benchmarks

How to **run** benchmarks in this directory. Primary example: **ring**. For building the compiler / producing `dist` and related workflow, see [`../HACKING.md`](../HACKING.md).

**Important:** `:ring:konanRun` (and the other `:*:konanRun` tasks here) does **not** build the Kotlin/Native compiler. It only compiles and runs the benchmark sources with whatever `konanc` is already at `kotlin.native.home` (default `../dist`). If `../dist` is stale or missing, you will measure the **wrong** compiler (or fail to run). Build or unpack the compiler you intend to test **before** running ring.

Gradle is always invoked from here via `../../gradlew` (kotlin repo root).

## Layout

```
performance/
├── ring/               # ring microbenchmarks (RingLauncher)
├── ...                 # other benchmarks
├── buildSrc/           # benchmarking plugin, RunKotlinNativeTask
├── gradle.properties   # defaults: nativeWarmup, attempts, kotlin.native.home
└── settings.gradle     # skips Apple-only modules when crossTarget is non-Apple
```

Other modules (`cinterop`, `startup`, `helloworld`, `numerical`, …) use the same `:module:konanRun` pattern. Apple-only modules (`objcinterop`, `swiftinterop`) are included only for Apple `crossTarget`.

## OHOS (`ohosArm64`) — full command

Prerequisites: `hdc` in `PATH`, device online (`hdc list targets`), device reachable.

```bash
cd kotlin-native/performance

hdc list targets

../../gradlew :ring:konanRun \
  -PcrossTarget=ohosArm64 \
  -PohosDeviceId=<serial> \
  -PnativeWarmup=0 \
  -Pattempts=1 \
  --filter=Fibonacci.calcClassic
# -PohosDeviceId: only when hdc list targets shows more than one device
```

This is a **smoke** run (warmup 0, 1 attempt, one filter). For a full suite, drop `--filter=…` and use defaults or set e.g. `-PnativeWarmup=10 -Pattempts=20` (defaults in `gradle.properties`; full ring is ~225 benchmarks, about **1–1.5 hours** on OHOS device with those defaults).

Again: this does **not** rebuild Kotlin — confirm `../dist` (or your `-Pkotlin.native.home` prebuilt) is the version you mean to measure.

`kotlin.native.home` defaults to `../dist` in `gradle.properties` — **do not set** `-Pkotlin.native.home` unless you intentionally want a prebuilt (see below).

Bootstrap KGP is already set in the **repo root** `gradle.properties` (`bootstrap.kotlin.version` / `bootstrap.kotlin.repo`) — **do not pass** `-Pbootstrap…` on the command line unless you need to override that default.

### What each part does

| Piece | Role |
|-------|------|
| `cd kotlin-native/performance` | Working directory for this guide; all relative paths below are from here. |
| `hdc list targets` | Confirm a device is connected. If **only one** device is listed, you can omit `-PohosDeviceId`. If more than one, copy the serial you want into `-PohosDeviceId`. |
| `:ring:konanRun` | Link the release kexe, push/run on device, write aggregated JSON under `ring/build/`. Uses existing `../dist` (or prebuilt); **does not build Kotlin** — wrong/stale `dist` ⇒ wrong measurement. |
| `-PcrossTarget=ohosArm64` | Cross-compile for OHOS arm64 and use hdc (not host execution). |
| `-PohosDeviceId=<serial>` | Pass `hdc -t <serial>`. **Only needed when more than one device is connected**; with a single device, omit it. |
| `-PnativeWarmup=N` | Warm-up iterations per benchmark (`gradle.properties` default `10`). |
| `-Pattempts=N` | Measured iterations per benchmark (default `20`). |
| `--filter=Name` | Comma-separated names from `ring`’s `list` output / `main.kt` keys, e.g. `Fibonacci.calcClassic`. |
| `--filterRegex=Pat` | Regex filter (optional alternative to `--filter`). |
| `-Pkotlin.native.home=…` | **Optional override.** Default is `../dist`. Only pass this to point at an unpacked Maven prebuilt. |
| `-Pbootstrap.kotlin.version` / `-Pbootstrap.kotlin.repo` | **Optional override.** Defaults come from repo-root `gradle.properties` (CPF bootstrap). Only pass to pin/override. |

**Naming:** `list` / `--filter` use names like `Fibonacci.calcClassic`. Aggregated reports often show `Ring::Fibonacci.calcClassic`. Manual device runs may use `-p Ring::` with `-f Fibonacci.calcClassic`.

### Related tasks

| Task | Purpose |
|------|---------|
| `:ring:konanRun` | Link + run + write `ring/build/nativeBenchResults.json` |
| `:ring:linkBenchmarkReleaseExecutableNative` | Link only |
| `:ring:konanJsonReport` | Build JSON report from bench results (runs after `konanRun`) |

List benchmarking tasks: `../../gradlew :ring:tasks --group=benchmarking`

### Artifacts (after `konanRun`)

- Host kexe (ohos arm64 ELF): `ring/build/bin/native/benchmarkReleaseExecutable/benchmark.kexe`
- Aggregated gradle output: `ring/build/nativeBenchResults.json`
- Device result file used by the runner: `/data/local/tmp/result.json`

### Optional: Maven prebuilt instead of `../dist`

Only when you need a published prebuilt (not the local dist):

```bash
KN_HOME=/tmp/kn-prebuilt/kotlin-native-prebuilt-macos-aarch64-2.2.21-0.5.0-10
mkdir -p /tmp/kn-prebuilt && cd /tmp/kn-prebuilt
curl -LO "https://maven.eazytec-cloud.com/nexus/repository/maven-public/org/jetbrains/kotlin/kotlin-native-prebuilt/2.2.21-0.5.0-10/kotlin-native-prebuilt-2.2.21-0.5.0-10-macos-aarch64.tar.gz"
tar xzf kotlin-native-prebuilt-2.2.21-0.5.0-10-macos-aarch64.tar.gz
```

Then add `-Pkotlin.native.home="$KN_HOME"` to the gradle command.

### Manual run on device (optional)

After `:ring:linkBenchmarkReleaseExecutableNative` (or a full `konanRun`):

```bash
hdc -t <serial> file send ring/build/bin/native/benchmarkReleaseExecutable/benchmark.kexe /data/local/tmp/
hdc -t <serial> shell chmod a+x /data/local/tmp/benchmark.kexe
hdc -t <serial> shell "LD_PRELOAD=/data/app/el1/bundle/public/com.huawei.hmos.location/libs/arm64/libc++_shared.so \
  /data/local/tmp/benchmark.kexe -p Ring:: -f Fibonacci.calcClassic -w 0 -r 1 \
  -o /data/local/tmp/result.json"
hdc -t <serial> shell cat /data/local/tmp/result.json
```

`LD_PRELOAD` supplies `libc++_shared` (required by the kexe). That path comes with the location app on typical devices; if the run fails to start, check that the `.so` exists on the device.

### Limits

- Device paths are fixed: kexe and result under `/data/local/tmp/`.
- **Do not** run two `:ring:konanRun` (or overlapping hdc ring runs) on the **same** device at once — they share the same remote paths.
- With multiple devices, pass one serial per invocation (`-PohosDeviceId`); with a single device, omit it.

## macOS host

Omit OHOS-only flags. Host preset is `macosArm64`. Reuse the same warmup/attempts and filter meanings as above.

```bash
cd kotlin-native/performance

../../gradlew :ring:konanRun \
  -PnativeWarmup=0 \
  -Pattempts=1 \
  --filter=Fibonacci.calcClassic
```

| vs OHOS | Difference |
|---------|------------|
| `-PcrossTarget` | **Omit** (host run, no hdc). |
| `-PohosDeviceId` | **Omit**. |
| Warmup / attempts / filter | Same as OHOS section. |
| `-Pkotlin.native.home` / bootstrap | Still optional; defaults from `gradle.properties` / repo root. |
| kexe | Mach-O at the same relative path under `ring/build/bin/native/…`. |
| Per-run JSON from the binary | `./result.json` under the process cwd (often `ring/` when launched from gradle). |
| Aggregated output | Still `ring/build/nativeBenchResults.json`. |

Manual host run:

```bash
./ring/build/bin/native/benchmarkReleaseExecutable/benchmark.kexe list
./ring/build/bin/native/benchmarkReleaseExecutable/benchmark.kexe \
  -p Ring:: -f Fibonacci.calcClassic -w 0 -r 1
```
