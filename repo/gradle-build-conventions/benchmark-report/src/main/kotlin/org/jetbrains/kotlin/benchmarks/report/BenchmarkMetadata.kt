/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */
package org.jetbrains.kotlin.benchmarks.report

import java.util.Locale

object BenchmarkMetadata {
    private const val BUNDLE = "benchmark-metadata"

    fun displayName(shortName: String, locale: Locale = Locale.ENGLISH): String =
        LocalizedMessages.get(BUNDLE, "$shortName.name", locale) ?: shortName

    fun description(shortName: String, locale: Locale = Locale.ENGLISH): String =
        LocalizedMessages.get(BUNDLE, "$shortName.description", locale)
            ?: LocalizedMessages.get(BUNDLE, "default.description", locale)
            ?: LocalizedMessages.get(BUNDLE, "default.description", Locale.ENGLISH)
            ?: shortName

    fun nameZh(shortName: String): String = displayName(shortName, Locale.SIMPLIFIED_CHINESE)

    fun descriptionZh(shortName: String): String = description(shortName, Locale.SIMPLIFIED_CHINESE)
}
