# benchmarks：编译器前端性能测试

基于 `[kotlinx-benchmark](https://github.com/Kotlin/kotlinx-benchmark)` 的微基准测试（底层采用 [JMH](https://github.com/openjdk/jmh) 微基准方法论与执行引擎），用于衡量 Kotlin 编译器前端分析（类型检查、数据流、控制流、类型推断等）在典型场景下的耗时。

**默认指标**：平均时间（avgt），单位 ms/op。

> 所有 Gradle 任务均在**仓库根目录**执行（即包含 `./gradlew` 的那一层）。

**语言**：[English](README.md) · [简体中文](README_zh.md)

---

## 目录结构

```
benchmarks/
├── src/org/jetbrains/kotlin/benchmarks/   # Benchmark 源码
│   ├── AbstractSimpleFileBenchmark.kt      # 基类：isIR 参数
│   ├── AbstractInferenceBenchmark.kt       # 基类：useNI 参数
│   ├── CommonCallsBenchmark.kt             # 常见函数调用
│   ├── SimpleDataFlowBenchmark.kt          # 简单数据流
│   ├── ComplexDataFlowBenchmark.kt         # 复杂数据流
│   ├── ControlFlowAnalysisBenchmark.kt    # 控制流分析
│   ├── ControlFlowOperators.kt            # 控制流操作符
│   ├── ManyValsBenchmark.kt                # 多 val 声明
│   ├── ManyVarsBenchmark.kt                # 多 var 声明
│   ├── ManyImplicitReceiversBenchmark.kt  # 多隐式接收者
│   ├── IntArrayPlusBenchmark.kt            # IntArray.plus 操作
│   ├── PlusAssignOperatorDesugaringBenchmark.kt  # += 解糖
│   ├── InferenceBaselineCallsBenchmark.kt       # 推理基线
│   ├── InferenceExplicitArgumentsCallsBenchmark.kt
│   ├── InferenceForInApplicableCandidate.kt
│   ├── InferenceFromArgumentCallsBenchmark.kt
│   └── InferenceFromReturnTypeCallsBenchmark.kt
├── baseline/
│   ├── benchmark-baseline.json          # 参考基线
│   └── .gitignore
└── build.gradle.kts
```

Gradle 插件源码：`repo/gradle-build-conventions/benchmark-report/`（注册任务 `compareBenchmarkResults`、`compareTestDuration`）。

---

## 快速开始

日常基准测试与基线对比，运行 **main 全量**即可：

```bash
./gradlew :benchmarks:benchmark
```

产出：终端末尾 `main summary:` 表格，以及 `benchmarks/build/reports/benchmarks/main/<时间戳>/main.json`（12 个基准类、27 条参数组合）。

与基线对比：

```bash
./gradlew :benchmarks:compareBenchmarkResults
```

开发过程中若改了 FIR / 控制流或类型推断，可用下方 [可选快速任务](#可选快速任务本地验证不支持基线对比) 缩短反馈时间

---

## 编译器参数

下面三个参数由 Gradle 任务传给编译器前端（正式基准测试与可选快速任务共用）。理解它们后，再看各任务的区别会更直观。

| 参数 | 含义 | 备注 |
|------|------|------|
| `isIR` | 前端分析路径 | `true` = FIR；`false` = 传统 K1 前端 |
| `useNI` | 是否启用新推断（New Inference） | 仅在 `isIR=false` 时生效 |
| `size` | 生成测试代码中的重复次数 | 各基准类在源码中声明了允许的取值；正式基准测试与快速任务均固定为 `1000` |

前端有两个**彼此独立**的维度：

1. **前端路径**：K1（`isIR=false`）还是 FIR（`isIR=true`）
2. **类型推断**（仅 K1）：旧推断（`useNI=false`）还是新推断（`useNI=true`）

---

## 正式基准测试：`:benchmarks:benchmark`

这是仓库里**唯一**纳入基线、对比脚本与总耗时检查的 benchmark 任务。

**干什么**：覆盖编译器前端主要场景，产出与 `benchmarks/baseline/benchmark-baseline.json` 对齐的 `main.json`，用于日常性能跟踪和回归对比。

**怎么用**：

```bash
./gradlew :benchmarks:benchmark
./gradlew :benchmarks:compareBenchmarkResults   # 与基线对比
```

**工具链**：`:benchmarks:compareBenchmarkResults` 读取最新 `main.json`，与 `benchmark-baseline.json` 对比。

**参数行为**：固定 `size=1000`；`isIR` 对每个基准扫描 `true` / `false`；推断类额外扫描 `useNI`（共 27 条，见下方明细）。

**27 条构成**：

| 类型 | 类 | 条数 |
|------|-----|------|
| 普通类 × `isIR` true/false | `CommonCalls`、`ComplexDataFlow`、`ControlFlowOperators`、`SimpleDataFlow`、`ManyVals`、`ManyVars`、`InferenceBaseline` | 7 × 2 = 14 |
| 推断类 × `isIR` / `useNI` 组合 | `InferenceExplicitArguments`、`InferenceForInApplicableCandidate`、`InferenceFromArgument`、`InferenceFromReturnType` | 4 × 3 = 12 |
| 仅 FIR（K1 路径会报错） | `IntArrayPlus` | 1 |

源码共 **15** 个具体 benchmark 类，正式基准测试实际写入 **12** 个。以下 3 个不在 `main.json` 中（非基线遗漏，而是配置原因）：

| 类 | 原因 | 替代方式 |
|----|------|---------|
| `ManyImplicitReceiversBenchmark` | 全局 `size=1000` 不在源码允许的值（1、10、50）内，运行时会跳过 | `runBenchmark` 并传合法 `size`（如 50） |
| `PlusAssignOperatorDesugaringBenchmark` | 同上，`size=1000` 不在源码允许的值（9…14）内 | `runBenchmark` 并传合法 `size`（如 12） |
| `ControlFlowAnalysisBenchmark` | `size=1000` 合法，但嵌套过深，正式基准测试易失败 | 见下方可选 FIR 快速任务（仅本地看终端输出） |

若要让前两个类纳入正式基准测试，需调整 `build.gradle.kts` 的 `size` 配置并更新基线。

---

## 可选快速任务（本地验证，不支持基线对比）

仓库另提供两个 Gradle 任务，**仅**用于开发时缩短基准测试耗时、在终端查看大致耗时：

| 任务 | 适用场景 | 覆盖 |
|------|---------|------|
| `:benchmarks:mainFirBenchmark` | 改了 FIR 或控制流，开发中快速看一眼 | `CommonCallsBenchmark`、`ControlFlowAnalysisBenchmark`（各 1 条，`isIR=true`） |
| `:benchmarks:mainNiBenchmark` | 改了 K1 新推断，开发中快速看一眼 | 5 个推断类（各 1 条，`isIR=false`, `useNI=true`） |

```bash
./gradlew :benchmarks:mainFirBenchmark
./gradlew :benchmarks:mainNiBenchmark
```

**限制**：

- **没有**对应基线；`:benchmarks:compareBenchmarkResults` **只**读取 `:benchmarks:benchmark` 产出的 `main.json`
- 两个任务测的是不同维度（FIR vs K1+新推断），**不能**拼起来代替正式基准测试
- 改了 FIR、控制流或推断后，可以先用它们做 sanity check，**提交前仍须跑** `:benchmarks:benchmark` 再做基线对比

`mainNiBenchmark` 覆盖：`InferenceBaselineCallsBenchmark`、`InferenceExplicitArgumentsCallsBenchmark`、`InferenceForInApplicableCandidate`、`InferenceFromArgumentCallsBenchmark`、`InferenceFromReturnTypeCallsBenchmark`。

---

## 结果输出



### 直接运行 benchmark

```bash
./gradlew :benchmarks:benchmark
```

产出：

- 控制台末尾 `main summary:` 表格
- `benchmarks/build/reports/benchmarks/main/<时间戳>/main.json`

---

## 与基线对比

### 前置条件

参考基线文件：`benchmarks/baseline/benchmark-baseline.json`

基线收录 **12 个类、27 条**参数组合，与一次成功的 `:benchmarks:benchmark` 产出的 `main.json` 对齐（每条 JSON 对象 = 一个 `benchmark` + `params`，不是「一个类一条」）。对比时请使用 benchmark 任务生成的 `main.json`，不要用 `runBenchmark` 随意改 `size` 后的结果，否则会出现大量「仅在基线中 / 仅在当前结果中」。

先跑 benchmark（若尚未跑过）：

```bash
./gradlew :benchmarks:benchmark
```


### Gradle 任务

```bash
./gradlew :benchmarks:compareBenchmarkResults
```

产出（位于 `benchmarks/baseline/reports/`）：

- `comparison.html` — 可视化报告
- `comparison.json`

常用 Gradle 属性：


| 属性                                              | 说明                            |
| ----------------------------------------------- | ----------------------------- |
| `-PbenchmarkCurrent=<path>`                     | 指定当前结果的 main.json 路径          |
| `-PbenchmarkCurrentText=<file>`                 | 使用保存的终端日志（需含 `main summary:`） |
| `-PbenchmarkThresholdPercent=<N>`               | 变化超过 ±N% 才判定为变好/差（默认 5）       |
| `-PbenchmarkFailIfRegressionExceedsPercent=<N>` | 变差项占比超过 N% 时任务失败（默认 5）        |
| `-PbenchmarkReportLocale=<lang>`                | 报告语言：`en`（英文）或 `zh`（简体中文，默认）  |


示例：

```bash
./gradlew :benchmarks:compareBenchmarkResults \
  -PbenchmarkCurrent=benchmarks/build/reports/benchmarks/main/2026-06-08T12.38.44.589098/main.json \
  -PbenchmarkReportLocale=en
```

---



## 三套测试总耗时检查

汇总 `tests-common-new`、`tests-spec`、`benchmarks` 的报告耗时，默认阈值 180 分钟。

### Gradle 任务

```bash
./gradlew compareTestDuration
```


| 属性                                   | 说明                        |
| ------------------------------------ | ------------------------- |
| `-PtestDurationThresholdMinutes=<N>` | 超时阈值（分钟，默认 180）           |
| `-PtestDurationAllowMissingReports=true` | 允许缺失或无法解析的报告，仅累加已有耗时（默认：要求三套齐全） |
| `-PbenchmarkReportLocale=<lang>`     | 报告语言：`en` 或 `zh`（默认 `zh`） |


示例：

```bash
./gradlew compareTestDuration -PbenchmarkReportLocale=en
```

---



## 报告语言（i18n）

对比报告与 Gradle 日志从 `repo/gradle-build-conventions/benchmark-report/src/main/resources/` 下的资源文件加载文案。


| 语言            | 资源文件              |
| ------------- | ----------------- |
| 英文（`en`）      | `*.properties`    |
| 简体中文（`zh`，默认） | `*_zh.properties` |


在 `compareBenchmarkResults` / `compareTestDuration` 上使用 `-PbenchmarkReportLocale=en` 或 `-PbenchmarkReportLocale=zh` 即可切换语言。

也接受别名：`english` → `en`，`zh-cn` / `chinese` → `zh`。

---



## 故障排查


| 问题                                                                | 解决方案                                                                                                               |
| ----------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------ |
| `Plugin ... org.jetbrains.kotlin.benchmarks.report was not found` | 确认已拉取含 `repo/gradle-build-conventions/benchmark-report/` 的代码，并在仓库根目录执行                                             |
| `没有可用的「当前基准结果」数据` / 找不到 main.json                                   | 先运行 `./gradlew :benchmarks:benchmark`，或 `-PbenchmarkCurrent=.../main.json`                 |
| `对比结果未通过`（BUILD FAILED）                                           | 报告仍会生成在 `benchmarks/baseline/reports/`；性能确实变差时会失败。仅查看报告可加 `-PbenchmarkFailIfRegressionExceedsPercent=101`          |
| 大量「仅在基线中 / 仅在当前结果中」                                               | 当前 JSON 与基线不是同一套 main 配置（例如用了 `runBenchmark` 改了 `size`，或对比了 fir/ni 的结果）；请用 `:benchmarks:benchmark` 产出的 `main.json` |
| 任务显示 UP-TO-DATE 无输出                                               | 已修复；仍可用 `--rerun-tasks` 强制重跑                                                                                       |
| 对比脚本找不到结果                                                         | 先成功执行一次 benchmark，确认 `benchmarks/build/reports/benchmarks/main/*/main.json` 存在                                     |


---



## 各基准说明


| 基准类                                        | 测试场景              | 纳入 main.json / 基线       |
| ------------------------------------------ | ----------------- | ----------------------- |
| `CommonCallsBenchmark`                     | 重复调用顶层函数          | 是                       |
| `SimpleDataFlowBenchmark`                  | 简单数据流分析           | 是                       |
| `ComplexDataFlowBenchmark`                 | 复杂数据流分析           | 是                       |
| `ControlFlowAnalysisBenchmark`             | 控制流分析             | 否（见可选 FIR 快速任务，不支持基线对比） |
| `ControlFlowOperators`                     | 控制流操作符处理          | 是                       |
| `ManyValsBenchmark`                        | 大量 val 声明         | 是                       |
| `ManyVarsBenchmark`                        | 大量 var 声明         | 是                       |
| `ManyImplicitReceiversBenchmark`           | 大量隐式接收者           | 否（`size` 与 main 配置冲突）   |
| `IntArrayPlusBenchmark`                    | IntArray.plus 操作符 | 是（仅 `isIR=true`）        |
| `PlusAssignOperatorDesugaringBenchmark`    | += 操作符解糖          | 否（`size` 与 main 配置冲突）   |
| `InferenceBaselineCallsBenchmark`          | 推理基线调用            | 是                       |
| `InferenceExplicitArgumentsCallsBenchmark` | 显式类型参数的推理调用       | 是                       |
| `InferenceForInApplicableCandidate`        | for-in 循环中候选查找的推理 | 是                       |
| `InferenceFromArgumentCallsBenchmark`      | 从实参推断返回类型         | 是                       |
| `InferenceFromReturnTypeCallsBenchmark`    | 从返回类型推断           | 是                       |


