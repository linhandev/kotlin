# Kotlin Native Stack Verification Mechanism

This document summarizes the current stack verification logic implemented in `VerifyKotlinStack.cpp`, `Memory.h`, and `ConcurrentMark.cpp`. The mechanism ensures the integrity of the Kotlin stack during runtime execution, specifically for interop transitions and garbage collection.

## 1. Core Principles

The verification system enforces strict rules on:
- **Stack Parity**: Entry and Exit frames must be pushed in pairs.
- **Frame Tags**: A specific magic number (`0xBEEF`) must be present in the high 16 bits of the stack map offset for Kotlin frames.
- **LIFO Consistency**: Frames must be pushed and popped in a strict Last-In, First-Out order.

## 2. Verification Points (Runtime Check)

These checks occur during normal execution (Push/Pop).

### 2.1 On Push Frame (`OnPushFrame`)

Based on the `FrameKind` being pushed:

#### A. Entry Frames (Entering Kotlin)
- **State Check**: The stack size **after** pushing must be **ODD**.
- **Consecutive Check**: Cannot push an Entry frame if the previous frame was also an Entry frame.

#### B. Exit Frames (Leaving Kotlin)
- **State Check**: The stack size **after** pushing must be **EVEN** (completing a pair).
- **Consecutive Check**: Cannot push an Exit frame if the previous frame was also an Exit frame.
- **Action**: Immediately triggers a **Stack Scan** (`ScanStackForTag`) for the newly closed pair.

#### C. Tag Verification (All Frames)
- **Positive Check**: If `IsKotlinFrame(kind)` is true, the frame **MUST** contain the valid `KOTLIN_STACK_TAG`.
- **Negative Check**: If `!IsKotlinFrame(kind)` is true (e.g., Native frame), the frame **MUST NOT** contain the `KOTLIN_STACK_TAG`.

### 2.2 On Pop Frame (`OnPopFrame`)

- **LIFO Check**: The `FrameKind` passed to `pop` must match the `FrameKind` recorded at the top of the shadow stack.
- **Note**: Parity checks are skipped on pop, relying on the strict checks performed during push and scan.

### 2.3 Stack Scanning (`ScanStackForTag`)

Triggered immediately when an Exit frame is pushed (closing a pair).

- **Scope**: Scans the frames between the top Exit frame (inclusive) and the matching Entry frame (exclusive).
- **Logic**:
    1.  Iterates through the linked list of frame pointers (`fp`).
    2.  Verifies that every frame in the chain has a valid `KOTLIN_STACK_TAG`.
    3.  If the Entry frame is a Kotlin frame (checked via `IsKotlinFrame(kind)`), it also verifies the tag on the Entry frame itself at `*(entryFp - 2)`.

## 3. Concurrent Marking Verification (`ConcurrentMark.cpp`)

These checks occur during the GC marking phase when traversing the stack.

### 3.1 Initial State Check
- **Parity Check**: Before walking the stack, verifies that the `fpStack` size is **EVEN**. If odd, it indicates an unbalanced stack (missing Restore call) and aborts.

### 3.2 Iteration Logic
- Iterates through the `fpStack` in pairs (Exit, Entry).
- **Pair Validation**: Checks that the frame at index `i` is an Exit frame and `i-1` is an Entry frame. If mismatch, aborts.

### 3.3 Stack Walking
- Walks the physical stack frames from Exit (`fp`) down to Entry (`stopFp`).
- **Loop**: `while (fp != stopFp)`
- **Safety**: Checks for `nullptr` to prevent infinite loops or segmentation faults.

### 3.4 Tag Verification (in `GetStackMapAddress`)
- When retrieving the stack map for a frame:
    - Checks `VerifyKotlinStack::IsKotlinFrameTag(fp)`.
    - If the tag is missing or invalid, logs a DFX error and aborts.
    - This implicitly verifies every frame visited during the marking phase.

### 3.5 Entry Caller Marking
- Uses `ShouldMarkEntryCaller(entryKind)` to determine if the frame *calling* into Kotlin (the Entry frame's caller) should also be marked.
- Logic: Returns `IsKotlinFrame(kind)`. If true, the Entry caller is processed and its tag is verified.

## 4. Frame Classification (`Memory.h`)

Helper functions define how frames are treated:

- **`IsEntryFrame(kind)`**: Returns true for:
    - Standard Runtime Entry: `kRuntimeToKotlin`, `kInitGlobals`, `kWorkerJob`.
    - Native to Kotlin (N2K): `kCExport`, `kBoxing`, `kUnboxing`, `kDisposeStableRef`, `kIsInstance`, `kClassInstance`, `kEnumEntry`.
    - Any frame where the *previous* frame was Unmanaged.

- **`IsExitFrame(kind)`**: Returns true for:
    - Kotlin to External: `kK2X`, `kWeakRef`, `kSafePoint`.
    - Kotlin to Native: `kNativeState`.
    - Global init adapter: `kGlobalInitAdapter`.

- **`IsKotlinFrame(kind)`**: Returns true for managed frames (Runtime or Kotlin), false for Unmanaged frames.

## 5. Implementation Details

- **Tag Value**: `0xBEEF` (High 16 bits).
- **Location**: `*(fp - 2)`.
- **Macros**: `ENABLE_VERIFY_STACK` controls compilation of verification logic.
