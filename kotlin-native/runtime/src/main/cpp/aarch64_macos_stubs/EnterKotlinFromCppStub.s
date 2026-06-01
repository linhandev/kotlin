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

// EnterKotlinFromCppStub (macOS / Mach-O variant)
//
// See aarch64_linux_ohos_stubs/EnterKotlinFromCppStub.s for full design notes.
//
// macOS specifics:
//   - Global C symbols carry a leading underscore.
//   - The post-blr return PC is exported as a .quad in __DATA,__const (not as
//     a non-private label inside the CFI region, which would trigger a bogus
//     compact-unwind entry with encoding=0).

    .text
    .align 2
    .global _EnterKotlinFromCppStub
_EnterKotlinFromCppStub:
    .cfi_startproc

    stp  x29, x30, [sp, #-80]!
    .cfi_adjust_cfa_offset 80
    .cfi_rel_offset x29, 0
    .cfi_rel_offset x30, 8

    mov  x29, sp
    .cfi_def_cfa_register x29

    stp  x19, x20, [sp, #48]
    .cfi_rel_offset x19, 48
    .cfi_rel_offset x20, 56

    stp  x21, x22, [sp, #64]
    .cfi_rel_offset x21, 64
    .cfi_rel_offset x22, 72

    // Stash fn_ptr / a0 / a1 in callee-saved regs before bl _SaveLastFrameAndStatus
    // clobbers x0..x18 (AArch64 caller-saved).
    mov  x19, x0
    mov  x21, x1
    mov  x22, x2

    mov  x0, x29
    bl   _SaveLastFrameAndStatus

    mov  x0, x21
    mov  x1, x22

    blr  x19

Lpc_enter_kt_from_cpp:
    mov  x20, x0

    mov  x0, x29
    bl   _RestoreLastFrameAndStatus

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

    .section __DATA,__const
    .align 3
    .global _unwindPCForEnterKotlinFromCppStub
_unwindPCForEnterKotlinFromCppStub:
    .quad Lpc_enter_kt_from_cpp