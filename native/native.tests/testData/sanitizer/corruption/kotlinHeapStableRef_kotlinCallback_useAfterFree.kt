// KIND: STANDALONE_NO_TR
// DISABLE_NATIVE: isAppleTarget=true
// DISABLE_NATIVE: isAppleTarget=false
//
// SKIP: looks like a sanitizer UAF scenario, but is NOT in sanitizer scope.
//   Same as kotlinHeapStableRef_kotlin_useAfterFree: StableRef handle/lifetime UAF via C callback, not tagged-heap access.
//   No ASAN/HWASAN banner expected; fixture throws Error. Skipped from sanitizer suite.
//
// Scenario: StableRef handle use-after-dispose (Kotlin in C callback).
//   Problem type: use-after-free (handle / lifetime).
//   Allocator: Kotlin garbage-collected heap (StableRef target object).
//   Corrupter: Kotlin in C callback (asStableRef / get after dispose).
//   Memory: GC object via StableRef handle.
//   Pointer: StableRef.asCPointer given to C; dispose; C calls back into Kotlin asStableRef.

// MODULE: cinterop
// FILE: kotlinHeapStableRef_kotlinCallback_useAfterFree.def
---
typedef void (*cb_t)(void* p);
static void invoke_cb(void* p, cb_t cb) { cb(p); }

// MODULE: main(cinterop)
// FILE: main.kt

@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*
import kotlinHeapStableRef_kotlinCallback_useAfterFree.*

fun main() {
    val ref = StableRef.create("payload")
    val raw = ref.asCPointer()
    ref.dispose()
    invoke_cb(raw, staticCFunction { p: COpaquePointer? ->
        try {
            val again = p!!.asStableRef<String>()
            val v = again.get()
            throw Error("StableRef UAF did not abort, value=$v")
        } catch (e: Error) {
            throw e
        } catch (t: Throwable) {
            throw Error("StableRef UAF secondary: $t")
        }
    })
}
