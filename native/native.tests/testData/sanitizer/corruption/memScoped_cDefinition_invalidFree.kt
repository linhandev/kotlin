// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
// Scenario: ECO-01 Arena/memScoped ownership transfer then C free (C model of ObjC late free).
//   Problem type: invalid free.
//   Allocator: memScoped (Arena-managed kotlinx.cinterop.nativeHeap / Kotlin_interop_malloc).
//   Corrupter: C free via interop after the scope has already cleared the Arena chunk.
//   Memory: cinterop native memory with lifetime owned by memScoped/Arena.
//   Pointer: allocArray inside memScoped returns an interior pointer (Arena chunk header
//   precedes user bytes). C stashes that interior pointer; scope exit frees the chunk base;
//   C free(userPtr) is then free-of-non-malloc / bad-free — models ObjC free() on a
//   memScoped buffer after scope exit (kotlin-native#3311).

// MODULE: cinterop
// FILE: memScoped_cDefinition_invalidFree.def
---
#include <stdlib.h>
static char* g_held;
static void c_stash(char* p) { g_held = p; }
static void c_free_held(void) { free(g_held); g_held = 0; }

// MODULE: main(cinterop)
// FILE: main.kt

@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*
import memScoped_cDefinition_invalidFree.*

fun main() {
    memScoped {
        val p = allocArray<ByteVar>(8)
        c_stash(p)
    }
    // Scope already freed the Arena chunk; C frees the interior user pointer.
    c_free_held()
}
