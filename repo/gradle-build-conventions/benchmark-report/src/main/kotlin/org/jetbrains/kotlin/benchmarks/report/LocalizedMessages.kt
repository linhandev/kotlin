/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */
package org.jetbrains.kotlin.benchmarks.report

import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.Locale
import java.util.Properties
import java.util.concurrent.ConcurrentHashMap

object LocalizedMessages {
    private val cache = ConcurrentHashMap<Pair<String, Locale>, Properties>()

    fun get(bundleName: String, key: String, locale: Locale = Locale.ENGLISH): String? =
        resolve(bundleName, key, locale)

    fun format(bundleName: String, key: String, locale: Locale = Locale.ENGLISH, vararg args: Any?): String {
        val template = resolve(bundleName, key, locale) ?: key
        return applyArgs(template, args)
    }

    fun require(bundleName: String, key: String, locale: Locale = Locale.ENGLISH, vararg args: Any?): String =
        format(bundleName, key, locale, *args)

    private fun resolve(bundleName: String, key: String, locale: Locale): String? =
        bundle(bundleName, locale).getProperty(key)?.takeIf { it.isNotBlank() }
            ?: bundle(bundleName, Locale.ENGLISH).getProperty(key)?.takeIf { it.isNotBlank() }

    private fun applyArgs(template: String, args: Array<out Any?>): String {
        var result = template.replace("\\n", System.lineSeparator())
        args.forEachIndexed { index, arg ->
            result = result.replace("{$index}", arg?.toString() ?: "")
        }
        return result
    }

    private fun bundle(bundleName: String, locale: Locale): Properties =
        cache.getOrPut(bundleName to locale) { loadProperties(bundleName, locale) }

    private fun loadProperties(bundleName: String, locale: Locale): Properties {
        val resourceBase = "org/jetbrains/kotlin/benchmarks/report/$bundleName"
        val languageSuffix = locale.language.takeIf { it.isNotEmpty() && !it.equals("en", ignoreCase = true) }
            ?.let { "_$it" }
            .orEmpty()
        val candidates = buildList {
            if (languageSuffix.isNotEmpty()) add("$resourceBase$languageSuffix.properties")
            add("$resourceBase.properties")
        }
        val loader = LocalizedMessages::class.java.classLoader
        for (path in candidates) {
            loader.getResourceAsStream(path)?.use { stream ->
                return Properties().apply {
                    load(InputStreamReader(stream, StandardCharsets.UTF_8))
                }
            }
        }
        return Properties()
    }
}

object ReportLocale {
    const val PROPERTY_NAME = "benchmarkReportLocale"

    val DEFAULT: Locale = Locale.SIMPLIFIED_CHINESE

    /** Parses [PROPERTY_NAME] Gradle property values such as `en`, `zh`, or `zh-CN`. */
    fun fromProperty(value: String?): Locale {
        if (value.isNullOrBlank()) return DEFAULT
        return when (value.trim().lowercase().replace('_', '-')) {
            "en", "english" -> Locale.ENGLISH
            "zh", "zh-cn", "chinese" -> Locale.SIMPLIFIED_CHINESE
            else -> Locale.forLanguageTag(value.trim().replace('_', '-'))
        }
    }
}
