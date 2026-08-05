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

class A {
    fun f(x: Int) = x + 13
}

fun f(x: Int): Int {
    val a = A()
    assertTrue(a.isStack())
    return a.f(x)
}

fun box(): String {
    assertEquals(f(42), 55)

    return "OK"
}
