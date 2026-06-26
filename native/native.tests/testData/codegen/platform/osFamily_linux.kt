// DISABLE_NATIVE: isAppleTarget=true
// DISABLE_NATIVE: targetFamily=OHOS
// DISABLE_NATIVE: targetFamily=MINGW
// DISABLE_NATIVE: targetFamily=ANDROID

import kotlin.test.*
import kotlin.native.*

@OptIn(kotlin.experimental.ExperimentalNativeApi::class)
fun box(): String {
    assertEquals(OsFamily.LINUX, Platform.osFamily)
    return "OK"
}
