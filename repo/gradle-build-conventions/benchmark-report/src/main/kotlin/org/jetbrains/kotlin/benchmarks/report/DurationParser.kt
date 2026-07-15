/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */
package org.jetbrains.kotlin.benchmarks.report

import java.time.Duration
import java.util.Locale
import java.util.regex.Pattern

object DurationParser {
    private val H = Pattern.compile("(\\d+(?:\\.\\d+)?)\\s*h")
    private val M = Pattern.compile("(\\d+(?:\\.\\d+)?)\\s*m(?!s|[a-z])")
    private val MS = Pattern.compile("(\\d+(?:\\.\\d+)?)\\s*ms")
    private val S = Pattern.compile("(\\d+(?:\\.\\d+)?)\\s*s")

    fun parse(durationStr: String): Duration? {
        val text = durationStr.trim()
        if (text.isEmpty()) return null

        var hours = 0.0
        var minutes = 0.0
        var seconds = 0.0
        var foundAny = false

        H.matcher(text).let {
            if (it.find()) {
                hours = it.group(1)!!.toDouble()
                foundAny = true
            }
        }
        M.matcher(text).let {
            if (it.find()) {
                minutes = it.group(1)!!.toDouble()
                foundAny = true
            }
        }

        val msMatcher = MS.matcher(text)
        var totalMillis = 0.0
        while (msMatcher.find()) {
            totalMillis += msMatcher.group(1)!!.toDouble()
            foundAny = true
        }

        if (totalMillis > 0.0) {
            val remaining = text.replace(Regex("\\d+(?:\\.\\d+)?\\s*ms"), "")
            S.matcher(remaining).let {
                if (it.find()) {
                    seconds = it.group(1)!!.toDouble()
                    foundAny = true
                }
            }
            seconds += totalMillis / 1000.0
        } else {
            S.matcher(text).let {
                if (it.find()) {
                    seconds = it.group(1)!!.toDouble()
                    foundAny = true
                }
            }
        }

        if (!foundAny) return null

        val totalSeconds = hours * 3600 + minutes * 60 + seconds
        return Duration.ofNanos((totalSeconds * 1_000_000_000).toLong())
    }

    fun format(duration: Duration): String {
        val totalSeconds = duration.seconds + duration.nano / 1_000_000_000.0
        val hours = (totalSeconds / 3600).toInt()
        val minutes = ((totalSeconds % 3600) / 60).toInt()
        val seconds = totalSeconds % 60
        return when {
            hours > 0 -> "${hours}h${minutes}m${"%.2f".format(seconds)}s"
            minutes > 0 -> "${minutes}m${"%.2f".format(seconds)}s"
            else -> "${"%.2f".format(seconds)}s"
        }
    }

    fun toMinutes(duration: Duration): Double =
        duration.seconds / 60.0 + duration.nano / 60_000_000_000.0

    fun formatWithMinutes(duration: Duration, locale: Locale = ReportLocale.DEFAULT): String {
        val formatted = format(duration)
        val minutes = toMinutes(duration)
        return ReportMessages.benchmarkCli("compare_duration.with_minutes", locale, formatted, "%.2f".format(minutes))
    }
}
