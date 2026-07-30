// KIND: STANDALONE_NO_TR
// EXIT_CODE: 0
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi
//
// Scenario: WeakReference installs ExtraObjectData; GC.collect runs finalizers.
// No intentional OOB/UAF. Expect exit 0 under suite defaults (gc/alloc/sanitizer).
//
// Regression: Finalizer restrace used to read typeInfoOrMeta_->instanceSize_ while
// SetValid bit59 still set (ptr tag 0x08 vs mmap shadow 0x00) → tag-mismatch FP.
// Fix: restrace size via type_info()/TypeInfo* (clears TBI / correct ExtraObject case).

@file:OptIn(
    kotlin.native.runtime.NativeRuntimeApi::class,
    kotlin.experimental.ExperimentalNativeApi::class,
)

import kotlin.native.ref.WeakReference
import kotlin.native.runtime.GC

private class HwasanTypeProbe

fun main() {
    var matched = 0

    repeat(8192) { index ->
        val strong: Any = HwasanTypeProbe()
        val weak = WeakReference(strong) // installs ExtraObjectData on strong
        val restored: Any? = weak.value

        // Dynamic TypeInfo read; fold into return so it cannot be DCE'd.
        if (restored is HwasanTypeProbe) {
            matched = matched xor (index + 1)
        }

        // Rotate addresses / HWASAN tags to raise hit rate on the conflict path.
        if ((index and 255) == 255) {
            GC.collect()
        }
    }

    println("EXTRAOBJECT_TYPEINFO_OK matched=$matched")
}
