// DISABLE_NATIVE: isAppleTarget=true
// DISABLE_NATIVE: targetFamily=LINUX
// DISABLE_NATIVE: targetFamily=MINGW
// DISABLE_NATIVE: targetFamily=ANDROID

import kotlin.test.*
import kotlin.native.*

@OptIn(kotlin.experimental.ExperimentalNativeApi::class)
fun box(): String {
    assertEquals(OsFamily.OHOS, Platform.osFamily)
    return "OK"
}
