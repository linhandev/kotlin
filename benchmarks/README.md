# benchmarks: compiler frontend performance tests

Micro-benchmarks based on [`kotlinx-benchmark`](https://github.com/Kotlin/kotlinx-benchmark) (JMH methodology and execution engine from [JMH](https://github.com/openjdk/jmh)) for measuring Kotlin compiler frontend analysis—type checking, data flow, control flow, type inference, and related work—in typical scenarios.

**Default metric**: average time (avgt), unit ms/op.

> Run all Gradle tasks from the **repository root** (the directory that contains `./gradlew`).

**Languages**: [English](README.md) · [简体中文](README_zh.md)

---

## Directory layout

```
benchmarks/
├── src/org/jetbrains/kotlin/benchmarks/   # Benchmark sources
│   ├── AbstractSimpleFileBenchmark.kt      # Base class: isIR parameter
│   ├── AbstractInferenceBenchmark.kt       # Base class: useNI parameter
│   ├── CommonCallsBenchmark.kt             # Common function calls
│   ├── SimpleDataFlowBenchmark.kt          # Simple data flow
│   ├── ComplexDataFlowBenchmark.kt         # Complex data flow
│   ├── ControlFlowAnalysisBenchmark.kt    # Control flow analysis
│   ├── ControlFlowOperators.kt            # Control-flow operators
│   ├── ManyValsBenchmark.kt                # Many val declarations
│   ├── ManyVarsBenchmark.kt                # Many var declarations
│   ├── ManyImplicitReceiversBenchmark.kt  # Many implicit receivers
│   ├── IntArrayPlusBenchmark.kt            # IntArray.plus
│   ├── PlusAssignOperatorDesugaringBenchmark.kt  # += desugaring
│   ├── InferenceBaselineCallsBenchmark.kt       # Inference baseline
│   ├── InferenceExplicitArgumentsCallsBenchmark.kt
│   ├── InferenceForInApplicableCandidate.kt
│   ├── InferenceFromArgumentCallsBenchmark.kt
│   └── InferenceFromReturnTypeCallsBenchmark.kt
├── baseline/
│   ├── benchmark-baseline.json          # Reference baseline
│   └── .gitignore
└── build.gradle.kts
```


Gradle plugin sources: `repo/gradle-build-conventions/benchmark-report/` (registers `compareBenchmarkResults` and `compareTestDuration`).

---

## Quick start

For day-to-day scoring and baseline comparison, run the **full main** suite:

```bash
./gradlew :benchmarks:benchmark
```

Produces the `main summary:` table on the console and `benchmarks/build/reports/benchmarks/main/<timestamp>/main.json` (12 benchmark classes, 27 parameter rows).

Compare against baseline:

```bash
./gradlew :benchmarks:compareBenchmarkResults
```

While developing FIR / control-flow or inference changes, use the [optional quick tasks](#optional-quick-tasks-local-only-not-for-baseline-comparison) below for faster feedback.

---

## Compiler parameters

The three parameters below are passed to the compiler frontend by Gradle (shared by the official run and the optional quick tasks). Read this first—it makes the tasks easier to tell apart.

| Parameter | Meaning | Notes |
|-----------|---------|-------|
| `isIR` | Frontend analysis path | `true` = FIR; `false` = classic K1 frontend |
| `useNI` | Enable New Inference | Only applies when `isIR=false` |
| `size` | Repeat count in generated test code | Each benchmark class declares allowed values in source; fixed to `1000` for the official run and quick tasks |

The frontend has two **independent** axes:

1. **Frontend path**: K1 (`isIR=false`) vs FIR (`isIR=true`)
2. **Type inference** (K1 only): old inference (`useNI=false`) vs New Inference (`useNI=true`)

---

## Official run: `:benchmarks:benchmark`

This is the **only** benchmark task integrated with the baseline, comparison tooling, and total-duration checks.

**Purpose**: Cover major frontend scenarios; produce `main.json` aligned with `benchmarks/baseline/benchmark-baseline.json` for tracking and regression checks.

**How to run**:

```bash
./gradlew :benchmarks:benchmark
./gradlew :benchmarks:compareBenchmarkResults   # compare against baseline
```

**Tooling**: `:benchmarks:compareBenchmarkResults` reads the latest `main.json` and compares it to `benchmark-baseline.json`.

**Parameter behavior**: `size=1000` is fixed; each benchmark sweeps `isIR` (`true` / `false`); inference benchmarks also sweep `useNI` (27 rows total; breakdown below).

**Breakdown of the 27 rows**:

| Kind | Classes | Rows |
|------|---------|------|
| General × `isIR` true/false | `CommonCalls`, `ComplexDataFlow`, `ControlFlowOperators`, `SimpleDataFlow`, `ManyVals`, `ManyVars`, `InferenceBaseline` | 7 × 2 = 14 |
| Inference × `isIR` / `useNI` combos | `InferenceExplicitArguments`, `InferenceForInApplicableCandidate`, `InferenceFromArgument`, `InferenceFromReturnType` | 4 × 3 = 12 |
| FIR only (K1 path errors) | `IntArrayPlus` | 1 |

There are **15** concrete benchmark classes in source; the official run writes **12** to `main.json`. These three never appear (configuration or workload limits, not a baseline gap):

| Class | Reason | Alternative |
|-------|--------|-------------|
| `ManyImplicitReceiversBenchmark` | Global `size=1000` is outside allowed source values (1, 10, 50); the run skips this class | `runBenchmark` with a valid `size` (e.g. 50) |
| `PlusAssignOperatorDesugaringBenchmark` | Same: `size=1000` is outside allowed source values (9…14) | `runBenchmark` with a valid `size` (e.g. 12) |
| `ControlFlowAnalysisBenchmark` | `size=1000` is valid, but nested loops are too heavy; the official run usually fails | Optional FIR quick task below (console output only) |

To include the first two in the official run, adjust `size` in `build.gradle.kts` and refresh the baseline.

---

## Optional quick tasks (local only, not for baseline comparison)

The repo also exposes two Gradle tasks **only** for shorter dev runs and rough console timings:

| Task | When | Coverage |
|------|------|----------|
| `:benchmarks:mainFirBenchmark` | Changed FIR or control flow; quick look while developing | `CommonCallsBenchmark`, `ControlFlowAnalysisBenchmark` (one row each, `isIR=true`) |
| `:benchmarks:mainNiBenchmark` | Changed K1 New Inference; quick look while developing | 5 inference classes (one row each, `isIR=false`, `useNI=true`) |

```bash
./gradlew :benchmarks:mainFirBenchmark
./gradlew :benchmarks:mainNiBenchmark
```

**Limitations**:

- **No** baseline; `:benchmarks:compareBenchmarkResults` **only** reads `main.json` from `:benchmarks:benchmark`
- The two tasks measure different axes (FIR vs K1 + New Inference) and **cannot** replace the official run together
- After FIR, control-flow, or inference changes, they are fine for a sanity check, but **you must still run** `:benchmarks:benchmark` before baseline comparison

`mainNiBenchmark` covers: `InferenceBaselineCallsBenchmark`, `InferenceExplicitArgumentsCallsBenchmark`, `InferenceForInApplicableCandidate`, `InferenceFromArgumentCallsBenchmark`, `InferenceFromReturnTypeCallsBenchmark`.

---

## Output



### Running benchmark directly

```bash
./gradlew :benchmarks:benchmark
```

Produces:

- `main summary:` table on the console
- `benchmarks/build/reports/benchmarks/main/<timestamp>/main.json`

---

## Compare against baseline

### Prerequisites

Reference baseline: `benchmarks/baseline/benchmark-baseline.json`

The baseline contains **12 classes and 27 parameter rows**, aligned with a successful `:benchmarks:benchmark` `main.json` (each JSON object is one `benchmark` + `params` row, not one row per class). For comparison, use `main.json` from the benchmark task—not ad-hoc `runBenchmark` runs with different `size` values—or you will see many “baseline only / current only” rows.

Run benchmark first (if not done yet):

```bash
./gradlew :benchmarks:benchmark
```


### Gradle task

```bash
./gradlew :benchmarks:compareBenchmarkResults
```

Reports (under `benchmarks/baseline/reports/`):

- `comparison.html` — visual report
- `comparison.json`

Common Gradle properties:


| Property | Description |
|----------|-------------|
| `-PbenchmarkCurrent=<path>` | Path to current `main.json` |
| `-PbenchmarkCurrentText=<file>` | Saved terminal log (must contain `main summary:`) |
| `-PbenchmarkThresholdPercent=<N>` | Mark improved/regressed only when change exceeds ±N% (default 5) |
| `-PbenchmarkFailIfRegressionExceedsPercent=<N>` | Fail when regression fraction exceeds N% (default 5) |
| `-PbenchmarkReportLocale=<lang>` | Report language: `en` (English) or `zh` (Simplified Chinese, default) |


Example:

```bash
./gradlew :benchmarks:compareBenchmarkResults \
  -PbenchmarkCurrent=benchmarks/build/reports/benchmarks/main/2026-06-08T12.38.44.589098/main.json \
  -PbenchmarkReportLocale=en
```

---



## Three-suite total duration check

Aggregates report durations from `tests-common-new`, `tests-spec`, and `benchmarks`. Default threshold: 180 minutes.

### Gradle task

```bash
./gradlew compareTestDuration
```


| Property | Description |
|----------|-------------|
| `-PtestDurationThresholdMinutes=<N>` | Timeout threshold in minutes (default 180) |
| `-PtestDurationAllowMissingReports=true` | Allow missing/unparseable reports; sum only available durations (default: require all three) |
| `-PbenchmarkReportLocale=<lang>` | Report language: `en` or `zh` (default `zh`) |


Example:

```bash
./gradlew compareTestDuration -PbenchmarkReportLocale=en
```

---



## Report locale (i18n)

Comparison reports and Gradle log messages load strings from resource bundles under `repo/gradle-build-conventions/benchmark-report/src/main/resources/`.


| Locale | Resource files |
|--------|----------------|
| English (`en`) | `*.properties` |
| Simplified Chinese (`zh`, default) | `*_zh.properties` |


Switch language with `-PbenchmarkReportLocale=en` or `-PbenchmarkReportLocale=zh` on `compareBenchmarkResults` / `compareTestDuration`.

Accepted aliases: `english` → `en`, `zh-cn` / `chinese` → `zh`.

---



## Troubleshooting


| Issue | Solution |
|-------|----------|
| `Plugin ... org.jetbrains.kotlin.benchmarks.report was not found` | Ensure code includes `repo/gradle-build-conventions/benchmark-report/` and run from repo root |
| No current benchmark data / `main.json` not found | Run `./gradlew :benchmarks:benchmark`, or pass `-PbenchmarkCurrent=.../main.json` |
| Compare check failed (BUILD FAILED) | Reports are still written to `benchmarks/baseline/reports/`; the task fails when performance actually regresses. To view reports only: `-PbenchmarkFailIfRegressionExceedsPercent=101` |
| Many “baseline only / current only” rows | Current JSON and baseline are not the same `main` configuration (e.g. `runBenchmark` with a different `size`, or comparing fir/ni output); use `main.json` from `:benchmarks:benchmark` |
| Task UP-TO-DATE with no output | Fixed; use `--rerun-tasks` to force re-run |
| Comparison script finds no results | Run benchmark successfully first; confirm `benchmarks/build/reports/benchmarks/main/*/main.json` exists |


---



## Benchmark reference


| Benchmark class | Scenario | In main.json / baseline |
|-----------------|----------|-------------------------|
| `CommonCallsBenchmark` | Repeated top-level function calls | Yes |
| `SimpleDataFlowBenchmark` | Simple data-flow analysis | Yes |
| `ComplexDataFlowBenchmark` | Complex data-flow analysis | Yes |
| `ControlFlowAnalysisBenchmark` | Control-flow analysis | No (see optional FIR quick task; not for baseline comparison) |
| `ControlFlowOperators` | Control-flow operators | Yes |
| `ManyValsBenchmark` | Many `val` declarations | Yes |
| `ManyVarsBenchmark` | Many `var` declarations | Yes |
| `ManyImplicitReceiversBenchmark` | Many implicit receivers | No (`size` conflicts with main config) |
| `IntArrayPlusBenchmark` | `IntArray.plus` operator | Yes (`isIR=true` only) |
| `PlusAssignOperatorDesugaringBenchmark` | `+=` operator desugaring | No (`size` conflicts with main config) |
| `InferenceBaselineCallsBenchmark` | Inference baseline calls | Yes |
| `InferenceExplicitArgumentsCallsBenchmark` | Calls with explicit type arguments | Yes |
| `InferenceForInApplicableCandidate` | Inference for `for-in` candidate lookup | Yes |
| `InferenceFromArgumentCallsBenchmark` | Inference from arguments | Yes |
| `InferenceFromReturnTypeCallsBenchmark` | Inference from return type | Yes |

