// KIND: STANDALONE_NO_TR
// DISABLE_NATIVE: isAppleTarget=true
// DISABLE_NATIVE: isAppleTarget=false
//
// SKIP: looks like a sanitizer UAF scenario, but is NOT in sanitizer scope.
//   KN HashMap concurrent Workers hit ConcurrentModificationException (language runtime)
//   before any tagged-heap UAF that ASAN/HWASAN would report. Output has no "sanitizer"
//   banner; keeping this under sanitizer-only OUTPUT_REGEX would be a permanent false FAIL.
//   Re-enable only if rewritten to a raw heap race that bypasses CME and reaches HWASAN/ASAN.
//
// Scenario: ECO-30 pure Kotlin concurrent HashMap race → Native heap use-after-free.
//   Problem type: use-after-free (data race on HashMap backing array).
//   Allocator: Kotlin garbage-collected heap (HashMap backing storage / arrays).
//   Corrupter: Kotlin concurrent put/resize vs iterate without synchronization (Workers).
//   Memory: garbage-collected heap (not FFI).
//   Pointer: shared HashMap mutated from multiple Workers + main; no locks.
@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class)

import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker

private fun hammer(map: HashMap<Int, ByteArray>, base: Int, rounds: Int): Int {
    var touch = 0
    for (i in 0 until rounds) {
        map[base + i] = ByteArray(32)
        if (i % 64 == 0) {
            for ((_, v) in map) {
                touch += v.size
                if (touch > 4096) break
            }
        }
    }
    return touch
}

fun main() {
    val map = HashMap<Int, ByteArray>()
    for (i in 0 until 32) {
        map[i] = ByteArray(8)
    }

    val rounds = 20_000
    val workers = 4
    val futures = Array(workers) { wi ->
        Worker.start().execute(TransferMode.SAFE, { Triple(map, wi * rounds, rounds) }) { (m, base, n) ->
            hammer(m, base, n)
        }
    }

    hammer(map, workers * rounds, rounds)

    var sum = 0
    for (f in futures) {
        sum += f.result
    }
    // If the race did not abort, force a failure so a silent miss is visible.
    error("HashMap race did not abort (touch=$sum size=${map.size})")
}
