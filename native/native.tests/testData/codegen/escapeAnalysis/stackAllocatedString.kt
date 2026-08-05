/*
 * Copyright 2010-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */
// IGNORE_NATIVE: optimizationMode=DEBUG
// IGNORE_NATIVE: optimizationMode=NO
// EA is force-disabled when precise stackmap is on: KonanConfig.enableStackmap
// defaults to ON for ohos_arm64 / macos_arm64, and TopLevelPhases force-disables
// EscapeAnalysis in that mode, so stack allocation never happens.
// IGNORE_NATIVE: target=ohos_arm64
// IGNORE_NATIVE: target=macos_arm64

import kotlin.test.*
import kotlin.native.internal.*

fun box(): String {
    val s = String()
    assertTrue(s.isStack())

    return "OK"
}
