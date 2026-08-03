/*
 * Copyright 2010-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */
// IGNORE_NATIVE: optimizationMode=OPT
// Negative test: asserts the object is NOT stack-allocated, so it is expected to
// fail under OPT where EA would allocate on stack. With precise stackmap on
// (ohos_arm64 / macos_arm64) EA is force-disabled, so this test now *passes* and
// the OPT expected-failure marker misfires. IGNORE_NATIVE has no negation, so the
// affected target/mode pairs are disabled.
// DISABLE_NATIVE: target=ohos_arm64 && optimizationMode=OPT
// DISABLE_NATIVE: target=macos_arm64 && optimizationMode=OPT

import kotlin.test.*
import kotlin.native.internal.*

fun box(): String {
    val s = String()
    assertFalse(s.isStack())

    return "OK"
}
