// IGNORE_NATIVE: optimizationMode=DEBUG
// IGNORE_NATIVE: optimizationMode=NO
// EA is force-disabled when precise stackmap is on: KonanConfig.enableStackmap
// defaults to ON for ohos_arm64 / macos_arm64, and TopLevelPhases force-disables
// EscapeAnalysis in that mode, so stack allocation never happens.
// IGNORE_NATIVE: target=ohos_arm64
// IGNORE_NATIVE: target=macos_arm64

// Enable runtime assertions:
// ASSERTIONS_MODE: always-enable

import kotlin.native.internal.*

class Foo

fun box(): String {
    val list = mutableListOf<Foo>()
    if (!list.isStack()) return "FAIL"

    list.add(Foo())

    @OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
    kotlin.native.runtime.GC.collect()

    return "OK"
}