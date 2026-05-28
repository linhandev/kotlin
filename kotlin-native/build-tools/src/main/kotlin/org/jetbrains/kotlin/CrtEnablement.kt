/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin

import org.gradle.api.Project
import org.jetbrains.kotlin.konan.target.KonanTarget

/**
 * Shared helpers for the precise-stackmap / CRT enablement decision. Called by both:
 *
 *   - `kotlin-native/runtime/build.gradle.kts` — to set `-DENABLE_STACKMAP=1`
 *     / `-DENABLE_CRT=1` on the runtime bitcode compile, and to gate the
 *     `crt` / `crt_alloc` / `cmc_gc` modules.
 *   - `kotlin-native/build.gradle` — to gate registering the libcrt.so
 *     build/copy tasks (`buildLibCrt`, `ohos_arm64CrossDistLibCrtCopy`).
 *
 * Property override priority (highest to lowest):
 *   1. `-Pkotlin.native.<flag>.<target_name>=true|false`  (per-target)
 *   2. `-Pkotlin.native.<flag>=true|false`                (global)
 *   3. Default: only `ohos_arm64`
 *
 * Invariant: CRT requires STACKMAP. STACKMAP can stand alone.
 * `kotlin.native.crt=true` + `kotlin.native.precise.stackmap=false` errors out.
 *
 * Rationale:
 *   - x86_*: unsupported by the precise-stackmap pipeline.
 *   - 32-bit platforms : unsupported by CRT
 */

fun resolveEnableStackmap(project: Project, target: KonanTarget): Boolean {
    val perTarget = project.findProperty("kotlin.native.precise.stackmap.${target.name}") as? String
    if (perTarget != null) return perTarget.toBoolean()
    val global = project.findProperty("kotlin.native.precise.stackmap") as? String
    if (global != null) return global.toBoolean()
    return target == KonanTarget.OHOS_ARM64
}

/**
 * True if CRT allocator + CMC GC are on for [target]. Default = stackmap value.
 * Explicit `kotlin.native.crt=true` combined with stackmap=false errors out.
 */
fun resolveEnableCrt(project: Project, target: KonanTarget): Boolean {
    val stackmap = resolveEnableStackmap(project, target)
    val perTarget = project.findProperty("kotlin.native.crt.${target.name}") as? String
    val global = project.findProperty("kotlin.native.crt") as? String
    val explicit = perTarget?.toBoolean() ?: global?.toBoolean()
    if (explicit == true && !stackmap) {
        error(
            "kotlin.native.crt=true is incompatible with kotlin.native.precise.stackmap=false " +
            "on target ${target.name}: CRT requires the precise-stackmap pipeline"
        )
    }
    return explicit ?: stackmap
}
