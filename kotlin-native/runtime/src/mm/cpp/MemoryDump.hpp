/*
 * Copyright 2024 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#pragma once

namespace kotlin::mm {

/**
 * RAII guard ensuring only one dump runs at a time.
 *
 * Uses non-blocking try-lock semantics: if another dump is already in
 * progress, acquisition fails and `operator bool()` returns false.
 * The caller should check the guard before proceeding:
 *
 *   DumpGuard guard;
 *   if (!guard) return false;  // another dump is running
 *
 * For the fork-based async path, the parent releases the flag immediately
 * after fork (in ~DumpGuard), while the child process inherits a "locked"
 * flag in its own address space — which is harmless since the child calls
 * _exit() without running destructors.
 */
class DumpGuard {
public:
    DumpGuard() noexcept;
    ~DumpGuard();
    explicit operator bool() const noexcept { return acquired_; }
    DumpGuard(const DumpGuard&) = delete;
    DumpGuard& operator=(const DumpGuard&) = delete;
private:
    bool acquired_;
};

/**
 * Dumps memory into the given POSIX file in raw Kotlin/Native Dump file format
 * (no compression, no fork), and returns success flag. Must be called during STW.
 *
 * The dump includes raw contents of Kotlin objects in binary form, together
 * with corresponding type layouts. The dump can be combined with additional
 * metadata emitted by the compiler and converted to the "hprof" format by an
 * external tool.
 *
 * This function matches the official Kotlin framework interface signature.
 */
bool DumpMemory(int fd) noexcept;

/**
 * Async version of DumpMemory that performs the dump with parallel compression
 * and fork-based isolation (OHOS only). On OHOS the dump runs in a forked child
 * process so the parent returns immediately.
 *
 * This is the version registered as the runtime callback for OHOS HiDebug
 * and used by OOM tracking.
 */
bool DumpMemoryAsync(int fd, bool isStrip) noexcept;

} // namespace kotlin::mm
