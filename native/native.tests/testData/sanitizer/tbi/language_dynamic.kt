// KIND: STANDALONE_NO_TR
// EXIT_CODE: 0
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi

@file:OptIn(kotlin.native.runtime.NativeRuntimeApi::class, kotlin.experimental.ExperimentalNativeApi::class)
import kotlin.native.runtime.GC

class Marker(val id: Int)

fun main() {
    val live = ArrayList<Marker>(256)
    for (i in 0 until 256) {
        live.add(Marker(i))
        if (i % 32 == 0) GC.collect()
    }
    var sum = 0
    for (m in live) sum += m.id
    println("TBI-C01-DYNAMIC OK sum=$sum")
}
