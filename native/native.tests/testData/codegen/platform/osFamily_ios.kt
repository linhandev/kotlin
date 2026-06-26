// DISABLE_NATIVE: isAppleTarget=false
// DISABLE_NATIVE: targetFamily=OSX
// DISABLE_NATIVE: targetFamily=TVOS
// DISABLE_NATIVE: targetFamily=WATCHOS

import kotlin.test.*
import kotlin.native.*

@OptIn(kotlin.experimental.ExperimentalNativeApi::class)
fun box(): String {
    assertEquals(OsFamily.IOS, Platform.osFamily)
    return "OK"
}
