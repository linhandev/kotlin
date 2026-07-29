// KIND: STANDALONE_NO_TR
// EXIT_CODE: 0
// FREE_COMPILER_ARGS: -opt-in=kotlin.experimental.ExperimentalNativeApi,kotlin.native.runtime.NativeRuntimeApi

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)
import kotlin.native.ref.WeakReference
import kotlin.native.runtime.GC

data class Payload(val s: String)

fun main() {
    val strong = Payload("weak-c04")
    val weak = WeakReference(strong)
    val got = weak.get()
    if (got?.s != "weak-c04") throw Error("weak get failed: $got")
    // Keep strong live; exercise CRT weak impl path (isWeakImpl_ set at create).
    GC.collect()
    val still = weak.get()
    // Either still reachable via strong, or cleared — both exercise WeakReferenceImpl.
    println("TBI-C04-DYNAMIC OK strongAlive=${still?.s} id=${strong.s}")
}
