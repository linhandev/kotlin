// DISABLE_NATIVE: isAppleTarget=true
// DISABLE_NATIVE: targetFamily=LINUX
// DISABLE_NATIVE: targetFamily=OHOS
// DISABLE_NATIVE: targetFamily=ANDROID

import kotlin.test.*
import kotlin.native.*

@OptIn(kotlin.experimental.ExperimentalNativeApi::class)
fun box(): String {
    assertEquals(OsFamily.WINDOWS, Platform.osFamily)
    return "OK"
}
