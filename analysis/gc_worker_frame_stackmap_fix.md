# GC Worker Frame Stackmap 崩溃定位与修复说明

## 背景

- 当前分析仓库：`/Users/suyue/KMP/code_2_2/kotlin`
- 对照仓库：`/Users/suyue/KMP/code_2_2/kotlin_hw`
- 关系：
  - `kotlin_hw` 是 Kotlin 2.0 基线，已包含 stackmap/runtime/GC 相关改造
  - `kotlin` 是 Kotlin 2.2 基线，在 `Replace with LLVM 15` 之后 cherry-pick 了 90+ 个来自 `kotlin_hw` 的 stackmap/runtime/GC 相关提交
- 主要测试框架：`/Users/suyue/KMP/code_2_2/upgrade_test/KN-performance-testcases`

在修复 `safePointFunctionPrologue` wrapper frame capture 问题（见 `analysis/safepoint_wrapper_frame_fix.md`）后，gc benchmark 仍剩 2 个失败用例：

- `CMSTestCaseB/CMSTestCaseB.kt`
- `CMSTestCaseE/CMSTestCaseE.kt`

本文档记录 CMSTestCaseB 的定位与修复过程。

## 运行环境

- `DEVELOPER_DIR=/Applications/Xcode_26.2.app/Contents/Developer`
- `PATH=/Users/suyue/KMP/code_2_2/kotlin/kotlin-native/dist/bin:$PATH`

## 原始现象

- CMSTestCaseB 在运行早期直接 `segmentation fault`
- 历史 probe 日志显示，坏 probe 上界落在 `Kotlin_Worker_invokeCFunction` / `WorkerExecuteLaunchpad` 一带
- 与已修掉的 safepoint wrapper 问题不同，崩溃点已不在 `Kotlin_mm_safePointFunctionPrologue`

## 定位过程

### 1. 确认是否为 GC 问题

使用 `-Xbinary=gc=noop` 编译并运行 CMSTestCaseB：

```bash
konanc CMSTestCaseB.kt -opt -Xbinary=gc=noop -o program_noop.kexe
./program_noop.kexe
```

结果：**EXIT_CODE=0**，正常运行。说明问题与 GC 相关。

### 2. lldb 捕获崩溃现场

使用 CMS GC 编译并运行：

```bash
konanc CMSTestCaseB.kt -opt -Xbinary=gc=cms -o program_cms.kexe
lldb -s lldb_script.txt ./program_cms.kexe
```

关键信息：

- **崩溃线程**：Main GC thread (thread #3)
- **崩溃位置**：`ConcurrentMark::markInSTW() + 1748`
- **崩溃原因**：`EXC_BAD_ACCESS (code=1, address=0x11daaec13)`
- **x8 寄存器**：`0x000000011daaec13`（非法访问地址）
- **调用链**：`markInSTW` → `completeMutatorsRootSet` → `tryCollectRootSet` → `CollectStackMapBaseRoot` → `GetStackMapAddress` / `StackMapBuilder`

### 3. 历史 probe 日志分析

从 `CMSTestCaseB_kt_test_cfg.log` 中的 stackmap-probe 信息：

```
[stackmap-probe] tid=11595300 ... pcSymbol=Kotlin_Worker_invokeCFunction + 56
  *(fp-2)=0x1042f30a0 stackMapAddress=0x1042f30a0 funcStartPC=0x104bf4228
  inActualRange=0 inExpectedRange=0
```

说明 GC 在 unwind 时遍历到了 `Kotlin_Worker_invokeCFunction` 的 frame，并尝试用 `*(fp-2)` 作为 stackmap 地址解析。但 `Kotlin_Worker_invokeCFunction` 是 C++ runtime 函数，**没有 stackmap 信息**，`*(fp-2)` 存放的是栈上的保存寄存器值，不是有效的 stackmap 地址。

### 4. 与 kotlin_hw 的 worker 路径对比

**kotlin_hw (2.0)**：

```cpp
case JOB_REGULAR: {
    CurrentFrameGuard guard;
    SaveThreadLastKotlinFrame2();
    job.regularJob.function(argument, resultHolder.slot());  // 直接调用 Kotlin 函数
    RestoreThreadLastKotlinFrame2();
}
```

**kotlin (2.2)**：

```cpp
case JOB_REGULAR: {
    SaveThreadLastKotlinFrame2();
    result.reset(WorkerExecuteLaunchpad(...));  // Kotlin 函数
    // WorkerExecuteLaunchpad 内部调用 invokeCFunction → Kotlin_Worker_invokeCFunction (C++)
    RestoreThreadLastKotlinFrame2();
}
```

关键差异：kotlin 2.2 的 worker 路径多了一层 C++ 函数 `Kotlin_Worker_invokeCFunction`，它夹在 `WorkerExecuteLaunchpad`（Kotlin）和 worker lambda（Kotlin）之间。该 C++ 函数没有 stackmap，但 GC 在遍历 frame pair 时会经过它，导致对非法 stackmap 地址的解析崩溃。

### 5. LLVM IR 与 addrspace 检查

使用 `-Xsave-llvm-ir-after=CStubs -Xsave-llvm-ir-directory=./llvm_ir` 导出 IR，检查 `bridge-DN main$lambda$0` 和 `main$lambda$0#internal`：

- 参数和局部变量正确使用 `ptr addrspace(1)`
- `gc "kotlin-native"` 属性正确
- IR 本身无异常，问题在 runtime 的 frame 遍历逻辑

## 根因总结

`tryCollectRootSet` 在遍历 frame pair 时，对每一层 frame 都调用 `CollectStackMapBaseRoot`，并假定 `*(fp-2)` 是有效的 stackmap 地址。当 frame pair 的边界跨越了 C++ runtime frame（如 `Kotlin_Worker_invokeCFunction`）时，该 frame 的 `*(fp-2)` 不是 stackmap 地址，导致 `StackMapBuilder` 解析非法内存并崩溃。

## 修复方案

在 `CollectStackMapBaseRoot` 中加入 stackmap 地址有效性检查：新增 `IsValidStackMapAddress()`，验证 `*(fp-2)` 取到的地址是否落在 `_LLVM_StackMaps`（或 `__LLVM_StackMaps`）段的合理范围内。若不在范围内，说明当前 frame 不是 Kotlin 函数，直接跳过，不尝试解析 stackmap。

这是一个**防御性修复**，不改变 frame pair 结构，而是让 GC 在遍历时能安全跳过非 Kotlin frame。

## 修改文件

- `kotlin-native/runtime/src/gc/cms/cpp/ConcurrentMark.cpp`
  - 新增 `IsValidStackMapAddress()`
  - 在 `CollectStackMapBaseRoot` 中调用该校验，无效则 `return`

## 修复后验证

| 用例 | 修复前 | 修复后 |
|------|--------|--------|
| CMSTestCaseB | SEGFAULT | 通过（5/5 次） |
| CMSTestCaseE | 已通过 | 通过（3/3 次） |
| CMSTestCaseA | 通过 | 通过 |
| CMSTestCaseC | 通过 | 通过 |
| BinaryTrees | 通过 | 通过 |
| Pidigits | 通过 | 通过 |

## 相关文档

- `analysis/safepoint_wrapper_frame_fix.md`：safepoint wrapper frame 误入 lastKotlinFrame 的修复
- `analysis/gc_remaining_failures_preliminary.md`：剩余 GC 失败用例的初步分析
