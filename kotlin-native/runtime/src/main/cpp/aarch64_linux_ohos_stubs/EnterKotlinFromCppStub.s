// Copyright (C) 2026 Huawei Device Co., Ltd.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

// EnterKotlinFromCppStub (OHOS aarch64 / GNU as)
//
// Independent N2K trampoline used when C++ runtime invokes a Kotlin function
// through a fn-ptr (4 call sites: findAssociatedObject / InitOrDeinitGlobalVariables /
// CallInitGlobalPossiblyLock / CallInitThreadLocal).
//
// Builds a real stub frame whose K2CSlotData slot is preserved across the call
// and addressable by the walker — replacing the old inline RAII pattern that
// required per-call-site unwindPCStartFor<XXX> PC range labels.
//
// Arity covers the actual call sites (max is currentNode->init(int, MemoryState*),
// 2 args).  Unused arg slots are passed as 0.
//
// Signature (C ABI):
//   void* EnterKotlinFromCppStub(void* fn_ptr, void* a0, void* a1);
//
// Calling convention:
//   x0 = fn_ptr (entry point to invoke)
//   x1 = a0     (forwarded as a0 to fn)
//   x2 = a1     (forwarded as a1 to fn)
//   return = x0 from fn_ptr
//
// Frame layout (80 bytes, 16-byte aligned).  K2CSlotData MUST live at offset 16
// from stub_fp so K2CSlotData* = stub_fp + OFFSET_K2C_SLOT_DATA addresses our
// slot region (OFFSET = 1 unit of FrameAddress = 16 bytes).
//
//   offset 0 (stub_fp)  | x29 (caller fp)
//   offset 8            | x30 (lr)
//   offset 16           | K2CSlotData.pc_      \
//   offset 24           | K2CSlotData.fa_       > 16B, written by SaveLastFrameAndStatus
//   offset 32           | status_/prev/padding /
//   offset 40           | (unused padding)
//   offset 48           | x19 saved (fn_ptr)
//   offset 56           | x20 saved (return value across RestoreLastFrameAndStatus)
//   offset 64           | x21 saved (a0 across SaveLastFrameAndStatus)
//   offset 72           | x22 saved (a1 across SaveLastFrameAndStatus)
//
// x21/x22 are needed because AArch64 ABI marks x1-x18 as caller-saved.  The bl
// to SaveLastFrameAndStatus may freely clobber x1 (=a0) and x2 (=a1); we must
// stash them in callee-saved regs before the bl and restore them after.
//
// IMPORTANT: deliberately does NOT touch ThreadData::anchorFA_.  Anchor stays
// per-thread one-shot (set in Kotlin_initRuntimeIfNeeded); rewriting anchor here
// under Kotlin/Native's pthread model would clip outer Kotlin frames out of GC
// root scan.
//
// Exception path: if the target unwinds via C++ exception, the asm frame has
// no dtor so RestoreLastFrameAndStatus is skipped.  lfi is left at the safe
// placeholder {null, Reliable, null} written at entry; the walker treats this
// as "no managed top frame" and skips the thread until next safepoint poll.

    .text
    .align 2
    .global EnterKotlinFromCppStub
    .type   EnterKotlinFromCppStub, @function
EnterKotlinFromCppStub:
    .cfi_startproc

    stp  x29, x30, [sp, #-80]!
    .cfi_adjust_cfa_offset 80
    .cfi_rel_offset x29, 0
    .cfi_rel_offset x30, 8

    mov  x29, sp
    .cfi_def_cfa_register x29

    // Save callee-saved registers we use across the bl calls below.
    stp  x19, x20, [sp, #48]
    .cfi_rel_offset x19, 48
    .cfi_rel_offset x20, 56

    stp  x21, x22, [sp, #64]
    .cfi_rel_offset x21, 64
    .cfi_rel_offset x22, 72

    // Stash fn_ptr / a0 / a1 in callee-saved regs before bl SaveLastFrameAndStatus
    // clobbers x0..x18 (AArch64 caller-saved).
    mov  x19, x0
    mov  x21, x1
    mov  x22, x2

    // entry: snapshot caller's lfi into [stub_fp+16..stub_fp+32]
    //        and publish lfi = {null, Reliable, null}.
    mov  x0, x29
    bl   SaveLastFrameAndStatus

    // Reload a0/a1 from x21/x22 and shift to x0/x1 to match fn_ptr's ABI.
    mov  x0, x21
    mov  x1, x22

    // Call the target Kotlin function via fn-ptr.
    blr  x19

    .global unwindPCForEnterKotlinFromCppStub
unwindPCForEnterKotlinFromCppStub:
    // walker recognises this PC as an R2K stub frame: type = K2N_STUB / R2K_STUB
    // and slot data is read from [stub_fp + 16].

    // Preserve fn_ptr's return value across the restore call.
    mov  x20, x0

    // exit: restore lfi from slot.
    mov  x0, x29
    bl   RestoreLastFrameAndStatus

    // Put fn_ptr's return value back into x0 before returning.
    mov  x0, x20

    ldp  x21, x22, [sp, #64]
    .cfi_restore x21
    .cfi_restore x22

    ldp  x19, x20, [sp, #48]
    .cfi_restore x19
    .cfi_restore x20

    ldp  x29, x30, [sp], #80
    .cfi_adjust_cfa_offset -80
    .cfi_restore x29
    .cfi_restore x30
    ret

    .cfi_endproc
    .size EnterKotlinFromCppStub, .-EnterKotlinFromCppStub
