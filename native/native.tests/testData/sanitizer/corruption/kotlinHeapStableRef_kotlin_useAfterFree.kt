// KIND: STANDALONE_NO_TR
// DISABLE_NATIVE: isAppleTarget=true
// DISABLE_NATIVE: isAppleTarget=false
//
// SKIP: looks like a sanitizer UAF scenario, but is NOT in sanitizer scope.
//   StableRef dispose + asStableRef/get is handle/lifetime UAF (ExternalRCRef), not a
//   tagged heap byte access. ASAN/HWASAN are not expected to print a sanitizer report;
//   the fixture only throws Error. Keep out of the sanitizer suite until rewritten as
//   raw heap corruption.
//
// Scenario: StableRef handle use-after-dispose (Kotlin).
//   Problem type: use-after-free (handle / lifetime).
//   Allocator: Kotlin garbage-collected heap (StableRef target object).
//   Corrupter: Kotlin (StableRef.asStableRef / get after dispose).
//   Memory: GC object reached via StableRef / ExternalRCRef handle (not a raw heap byte buffer).
//   Pointer: StableRef.create -> asCPointer; dispose; asStableRef/get again.
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*

fun main() {
    val ref = StableRef.create("hello")
    val ptr = ref.asCPointer()
    ref.dispose()
    // Handle UAF: force observable failure if runtime does not abort.
    try {
        val again = ptr.asStableRef<String>()
        val v = again.get()
        throw Error("StableRef UAF did not abort, value=$v")
    } catch (e: Error) {
        throw e
    } catch (t: Throwable) {
        throw Error("StableRef UAF secondary: $t")
    }
}
