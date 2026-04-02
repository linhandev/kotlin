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

#define cfi_adjust_cfa_offset(off)      .cfi_adjust_cfa_offset off
#define cfi_rel_offset(reg, off)        .cfi_rel_offset reg, off
#define cfi_restore(reg)                .cfi_restore reg
#define cfi_def_cfa_register(reg)       .cfi_def_cfa_register reg

////////////////////////////////////////////////////////////////////////////////
//
// ForwardStub simply forwards arguments passed by runtime, i.e., arguments for compiled kotlin method are passed
// according to C/C++ calling convention, which usually means efficiency.
//
////////////////////////////////////////////////////////////////////////////////

#define ForwardStubFrameSize      (8 * 36)
#define ForwardStubCalleeSaveArea (8 * 6)
#define FuncAddrAndCpStacksizeOffset  (8 * 2)

// _K2NForwardStubXX builds a stub frame to invoke the target kotlin method according to the previous frame which invokes
// _K2NForwardStubXX(kotlin_method, std::forward<Args>(args)...).
// R means runtime, while C means compiled kotlin method. XX indicates the return type of this kotlin method.

// On execution of "bl _K2NForwardStubXX", the frame layout of stack(growing downwards) looks like:
// x0: the entry point of kotlin method to be invoked
// x1~x7: hold the first 7 arguments arg0~arg6 if existed
// x30: return address of "bl _K2NForwardStubXX"
// all on-stack arguments are addressable by SP as the frame layout shows.
// arg7 will be passed to x7 from caller's stack after _K2NForwardStub is built.
//                 |  ...         |
//                 |  x30         | lr for the caller of _K2NForwardStubXX
// caller fp  -->  |  x29         |
//                 |  ...         |
//                 | arg11        |
//                 | arg10        |
//                 | arg9         |
//                 | arg8         |
// caller sp  -->  | arg7         |

// the frame layout of stack(growing downwards) after _K2NForwardStub frame is built looks like:
//                 |  ...         |
//                 |  x30         | lr for the caller of _K2NForwardStubXX
// caller fp  -->  |  x29         |
//                 |  ...         |
//                 | arg11        |
//                 | arg10        |
//                 | arg9         |
//                 | arg8         |
// caller sp  -->  | arg7         |
// callee saved    | r28          | <== _K2NForwardStub frame starts from here
//                 | r27          |
//                 | r26          |
//                 | r25          |
//                 | r24          |
//                 | r23          |
//                 | r22          |
//                 | r21          |
//                 | r20          |
// callee saved    | r19          |
//                 | padding      | the information of caller frame which is interpreted
//                 | UC Status    | unwind context status of caller frame
//                 | Context FP   | FP of unwind context frame
// K2CSlotData     | Context PC   | PC of unwind context frame
//                 | x30          |
//   stub fp  -->  | caller fp    |  // fp作为入参传给_SetLastFrameReliable
//                 |  ...         |
//                 | arg11        |
//                 | arg10        |
//                 | arg9         |
//   stub sp  -->  | arg8         | <== _K2NForwardStub frame ends at here

    .text
    .align 2
    .global Kotlin_N2KStub
Kotlin_N2KStub:
        // x10 = cpStackSize, x9 = calleeAddr
        ldp  x9, x10, [sp]
        add  sp, sp, #16

        .cfi_startproc
        stp  x29, x30, [sp,  #-288]!
        .cfi_adjust_cfa_offset 288
        .cfi_rel_offset x29, 0
        .cfi_rel_offset x30, 8

        // save all used callee-saved registers.
        stp  x19, x20, [sp, #48]
        .cfi_rel_offset x19, 48
        .cfi_rel_offset x20, 48+8

        stp  x21, x22, [sp, #48+0x10]
        .cfi_rel_offset x21, 48+0x10
        .cfi_rel_offset x22, 48+0x18

        stp  x23, x24, [sp, #48+0x20]
        .cfi_rel_offset x23, 48+0x20
        .cfi_rel_offset x24, 48+0x28

        stp  x25, x26, [sp, #48+0x30]
        .cfi_rel_offset x25, 48+0x30
        .cfi_rel_offset x26, 48+0x38

        stp  x27, x28, [sp, #48+0x40]
        .cfi_rel_offset x27, 48+0x40
        .cfi_rel_offset x28, 48+0x48

        stp  q0, q1, [sp, #48+0x50]
        .cfi_rel_offset q0, 48+0x50
        .cfi_rel_offset q1, 48+0x60

        stp  q2, q3, [sp, #48+0x70]
        .cfi_rel_offset q2, 48+0x70
        .cfi_rel_offset q3, 48+0x80

        stp  q4, q5, [sp, #48+0x90]
        .cfi_rel_offset q4, 48+0x90
        .cfi_rel_offset q5, 48+0xa0

        stp  q6, q7, [sp, #48+0xb0]
        .cfi_rel_offset q6, 48+0xb0
        .cfi_rel_offset q7, 48+0xc0

        // x19 <- previous fp
        //mov  x19, x29 // fp for the caller of _K2NForwardStubXX

        mov  x20, x0
        mov  x21, x1
        mov  x22, x2
        mov  x23, x3
        mov  x24, x4
        mov  x25, x5
        mov  x26, x6
        mov  x27, x7

        mov  x19, x10

        // x28 <- previous sp
        add  x28, sp, #288

        // x19 <- previous sp + cpStackSize
        add  x19, x28, x19

        mov  x29, sp
        .cfi_def_cfa_register x29

        stp  x9, x10, [sp, #48+0xd0]
        .cfi_rel_offset x9, 48+0xd0
        .cfi_rel_offset x10, 48+0xd8

        // frame info: tls -> stub
        mov  x0, x29
        bl SaveLastFrameAndStatus

        // prepare arguments for invoking target kotlin method
        mov  x0, x20
        mov  x1, x21
        mov  x2, x22
        mov  x3, x23
        mov  x4, x24
        mov  x5, x25
        mov  x6, x26
        mov  x7, x27

        ldp  q0, q1, [sp, #48+0x50]
        .cfi_restore q0
        .cfi_restore q1

        ldp  q2, q3, [sp, #48+0x70]
        .cfi_restore q2
        .cfi_restore q3

        ldp  q4, q5, [sp, #48+0x90]
        .cfi_restore q4
        .cfi_restore q5

        ldp  q6, q7, [sp, #48+0xb0]
        .cfi_restore q6
        .cfi_restore q7

        ldp  x9, x10, [sp, #48+0xd0]
        .cfi_restore x9
        .cfi_restore x10
        // copy arg9, arg10, ... (if existed)
.L_copy:
        cmp x19, x28
        ble .L_copy_end
        ldp x25, x26, [x19, #-16]!
        // SP is always 16 byte-aligned.
        stp x25, x26, [sp,  #-16]!
        b .L_copy
.L_copy_end:

        blr  x9
    .global unwindPCForN2KStub
unwindPCForN2KStub:
        /* keep potential return value */
        mov  x21, x0
        mov  x22, x1
        mov  x23, x2
        mov  x24, x3
        mov  x25, x8

        mov  sp, x29
        .cfi_def_cfa_register sp

        stp  d0, d1, [sp, #48+0x50]
        .cfi_rel_offset d0, 48+0x50
        .cfi_rel_offset d1, 48+0x58

        stp  d2, d3, [sp, #48+0x60]
        .cfi_rel_offset d2, 48+0x60
        .cfi_rel_offset d3, 48+0x68

        /* restore last_frame */
        mov  x0, x29
        bl RestoreLastFrameAndStatus

        /* set potential return value */
        mov  x0, x21
        mov  x1, x22
        mov  x2, x23
        mov  x3, x24
        mov  x8, x25

        ldp  d0, d1, [sp, #48+0x50]
        .cfi_restore d0
        .cfi_restore d1

        ldp  d2, d3, [sp, #48+0x60]
        .cfi_restore d2
        .cfi_restore d3

        // restore all used callee-saved registers.
        ldp  x19, x20, [sp, #48]
        .cfi_restore x19
        .cfi_restore x20
        ldp  x21, x22, [sp, #48+0x10]
        .cfi_restore x21
        .cfi_restore x22
        ldp  x23, x24, [sp, #48+0x20]
        .cfi_restore x23
        .cfi_restore x24
        ldp  x25, x26, [sp, #48+0x30]
        .cfi_restore x25
        .cfi_restore x26
        ldp  x27, x28, [sp, #48+0x40]
        .cfi_restore x27
        .cfi_restore x28

        ldp    x29, x30, [sp], #288
        .cfi_adjust_cfa_offset -288
        .cfi_restore x29
        .cfi_restore x30
        ret
        .cfi_endproc