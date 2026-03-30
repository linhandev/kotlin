# ICmp AddrSpace 类型不匹配 Bug 分析修复报告

## 1. 问题概述

### 1.1 现象

在 Kotlin 2.2 + stackmap 改造环境下，部分测试用例编译失败，LLVM 模块验证报错：

```
error: compilation failed: Invalid LLVM module
Verification errors:
    Both operands to ICmp instruction are not of the same type!
      %49 = icmp eq ptr addrspace(1) %48, ptr @839, !dbg !11891
```

另一类错误为 LLVM 后端 pass 报错：

```
fatal error: error in backend: reference should be in stack slot!
```

### 1.2 影响用例

| 用例 | 错误类型 | 根因 |
|------|----------|------|
| `TestStringDeduplicationInterned.kt` | ICmp 操作数类型不一致 | `ptr addrspace(1)` vs `ptr` |
| `ResultTest.kt` | reference should be in stack slot | 常量与堆引用 addrspace 混用导致 stackmap 解析异常 |

---

## 2. 根因分析

### 2.1 问题链路

当 Kotlin 代码执行 `str === "SharedStringContent"` 这类引用比较时：

```
str === "SharedStringContent"
  ↓ IrToBitcode.kt: evaluateOperatorCall → ib.eqeqeqSymbol → icmpEq(args[0], args[1])
  ↓ args[0]: str（循环变量）→ 堆引用 → ptr addrspace(1)
  ↓ args[1]: "SharedStringContent" → evaluateStringConst → kotlinStringLiteral
              → createKotlinStringLiteral → createConstant(header)
              → global.pointer.getElementPtr(...).bitcast(kObjHeaderPtr) → ptr (addrspace 0)
  ↓ icmpEq: LLVMBuildICmp(arg0=ptr addrspace(1), arg1=ptr)
  ↓ LLVM verifier: Both operands to ICmp instruction are not of the same type!
```

**核心矛盾**：堆对象引用使用 `ptr addrspace(1)`（GC 堆地址空间），而字符串字面量等常量使用 `ptr`（默认 addrspace 0），两者在 ICmp 中直接比较违反 LLVM IR 类型规则。

### 2.2 为何需要 addrspace(1)

在 Kotlin/Native 的 stackmap/GC 改造中：

- **堆对象引用**：必须使用 `ptr addrspace(1)`，以便 LLVM 的 stackmap pass 正确识别哪些栈槽/寄存器中存放的是 GC 可追踪引用
- **常量对象**：若与堆引用混用（如 `===` 比较、phi 合并等），类型必须一致，否则 LLVM 验证失败或 stackmap 解析出错

---

## 3. KN 2.2 与 KN 2.0 的差异影响

### 3.1 字符串字面量生成路径差异

| 版本 | 仓库 | `createKotlinStringLiteral` 实现 | 返回类型 |
|------|------|----------------------------------|----------|
| **KN 2.0** | kotlin_hw | 调用 `createConstKotlinArray` → `createRef(objHeaderPtr)` | `ptr addrspace(1)` |
| **KN 2.2** | kotlin | 调用 `createConstant(header)` → `bitcast(kObjHeaderPtr)` | `ptr` (addrspace 0) |

### 3.2 2.0 版本为何不暴露问题

在 kotlin_hw（2.0）中，`createKotlinStringLiteral` 的实现为：

```kotlin
private fun createKotlinStringLiteral(value: String): ConstPointer {
    val elements = value.toCharArray().map(llvm::constChar16)
    val objRef = createConstKotlinArray(context.ir.symbols.string.owner, elements)
    return objRef  // createConstKotlinArray 内部调用 createRef，返回 addrspace(1)
}
```

`createConstKotlinArray` 最终调用 `createRef(objHeaderPtr)`，将 `ptr` 转为 `ptr addrspace(1)`，因此字符串字面量在 2.0 中天然与堆引用类型一致。

### 3.3 2.2 版本为何暴露问题

在 kotlin（2.2）中，字符串实现改为更紧凑的 layout（`runtime.stringHeaderType` + flexible array），不再走 `createConstKotlinArray`：

```kotlin
private fun createKotlinStringLiteral(value: String): ConstPointer {
    // ... 构建 header（Struct 含 typeInfo、length、hashCode、flags、data）
    return createConstant(header)  // 直接返回 ptr，未经过 createRef
}
```

`createConstant` 返回 `bitcast(kObjHeaderPtr)`，即 addrspace 0。2.2 的架构演进导致字符串字面量路径与 2.0 不同，遗漏了 addrspace 统一。

### 3.4 差异影响小结

| 维度 | KN 2.0 | KN 2.2 | 影响 |
|------|--------|--------|------|
| 字符串 layout | CharArray 风格 | StringHeader + flexible array | 2.2 不再走 createConstKotlinArray |
| 常量 addrspace | createRef 统一为 addrspace(1) | createConstant 返回 addrspace(0) | 2.2 中常量与堆引用类型不一致 |
| ICmp 比较 | 两边均为 addrspace(1) | 一边 addrspace(1) 一边 addrspace(0) | 2.2 触发 LLVM verifier 错误 |
| Stackmap pass | 引用类型一致 | 混用导致 "reference should be in stack slot" | 2.2 中 Result 等用例崩溃 |

---

## 4. 修复方案

### 4.1 修复 1：KotlinStaticData.kt

**文件**：`kotlin-native/backend.native/compiler/ir/backend.native/src/org/jetbrains/kotlin/backend/konan/llvm/KotlinStaticData.kt`

**修改**：让 `createKotlinStringLiteral` 返回 `ptr addrspace(1)`，与 2.0 行为对齐。

```kotlin
// 修复前
return createConstant(header)

// 修复后
return createRef(createConstant(header))
```

`createRef` 通过 `addrbitcast(kObjHeaderRef)` 将 `ptr` 转为 `ptr addrspace(1)`。

### 4.2 修复 2：CodeGenerator.kt

**文件**：`kotlin-native/backend.native/compiler/ir/backend.native/src/org/jetbrains/kotlin/backend/konan/llvm/CodeGenerator.kt`

**修改**：在 `icmpEq` 和 `icmpNe` 中增加 addrspace 统一逻辑，作为防御性修复。

```kotlin
private fun unifyAddrSpace(arg0: LLVMValueRef, arg1: LLVMValueRef): Pair<LLVMValueRef, LLVMValueRef> {
    val t0 = LLVMTypeOf(arg0)!!
    val t1 = LLVMTypeOf(arg1)!!
    if (LLVMGetTypeKind(t0) != LLVMTypeKind.LLVMPointerTypeKind ||
        LLVMGetTypeKind(t1) != LLVMTypeKind.LLVMPointerTypeKind) return arg0 to arg1
    val as0 = LLVMGetPointerAddressSpace(t0)
    val as1 = LLVMGetPointerAddressSpace(t1)
    if (as0 == as1) return arg0 to arg1
    return arg0 to LLVMBuildAddrSpaceCast(builder, arg1, t0, "")!!
}

fun icmpEq(arg0: LLVMValueRef, arg1: LLVMValueRef, name: String = ""): LLVMValueRef {
    val (a, b) = unifyAddrSpace(arg0, arg1)
    return LLVMBuildICmp(builder, LLVMIntPredicate.LLVMIntEQ, a, b, name)!!
}

fun icmpNe(arg0: LLVMValueRef, arg1: LLVMValueRef, name: String = ""): LLVMValueRef {
    val (a, b) = unifyAddrSpace(arg0, arg1)
    return LLVMBuildICmp(builder, LLVMIntPredicate.LLVMIntNE, a, b, name)!!
}
```

当两个指针操作数 addrspace 不同时，将 `arg1` 转为 `arg0` 的类型，保证 ICmp 操作数类型一致。使用 `LLVMBuildAddrSpaceCast` 而非 `bitcast`，符合 LLVM 对 addrspace 转换的语义要求。

---

## 5. 验证结果

### 5.1 修复前

| 用例 | 编译 | 运行 |
|------|------|------|
| TestStringDeduplicationInterned.kt | ❌ ICmp 类型不匹配 | — |
| ResultTest.kt | ❌ reference should be in stack slot | — |

### 5.2 修复后

| 用例 | 编译 | 运行 |
|------|------|------|
| TestStringDeduplicationInterned.kt | ✅ | ✅ PASS |
| ResultTest.kt | ✅ | ✅ PASS |
| stringdedup 全部 6 个用例 | ✅ | ✅ PASS |

### 5.3 回归风险

- `createRef` 仅作用于字符串字面量，不影响其他常量路径
- `unifyAddrSpace` 仅在指针类型且 addrspace 不同时插入 `AddrSpaceCast`，对整数等非指针比较无影响
- 与 bak_patch 0032/0040/0050 的关系见 `gc_test_cases_api_migration_fix.md`

---

## 6. 提交信息

```
fix: ICmp addrspace mismatch for string literal vs heap reference (KN 2.2)

- KotlinStaticData: wrap createKotlinStringLiteral result with createRef
  so string literals return ptr addrspace(1), matching heap ref type
- CodeGenerator: add unifyAddrSpace in icmpEq/icmpNe to handle mixed
  ptr/ptr addrspace(1) operands via AddrSpaceCast

Fixes: TestStringDeduplicationInterned.kt, ResultTest.kt
Root cause: KN 2.2 string layout no longer goes through createConstKotlinArray
(which used createRef), so createConstant returns ptr addrspace(0) while
heap refs use ptr addrspace(1), causing LLVM verifier error on === comparison.
```

---

## 7. 相关文档

- `analysis/gc_test_cases_api_migration_fix.md`：5 个失败用例的完整分析与验证命令
- `bak_patch/0032-fix-icmpeq-type-mimatch.patch`：历史 icmpEq 修复尝试
- `bak_patch/0050-fix-icmp-theUnitInstance-initailizer-evaluateCast-bu.patch`：unique/Unit 的 addrspace 修复（已 cherry-pick）
