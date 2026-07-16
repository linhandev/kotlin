// DISABLE_NATIVE: isAppleTarget=false
// DISABLE_NATIVE: targetFamily=OSX
// DISABLE_NATIVE: targetFamily=IOS
// DISABLE_NATIVE: targetFamily=TVOS

import kotlin.test.*
import kotlin.native.*

@OptIn(kotlin.experimental.ExperimentalNativeApi::class)
fun box(): String {
    assertEquals(OsFamily.WATCHOS, Platform.osFamily)
    return "OK"
}
