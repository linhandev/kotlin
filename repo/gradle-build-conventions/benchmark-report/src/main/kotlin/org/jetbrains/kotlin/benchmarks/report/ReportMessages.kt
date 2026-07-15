/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */
package org.jetbrains.kotlin.benchmarks.report

import java.util.Locale

object ReportMessages {
    private const val BENCHMARK_REPORT = "benchmark-report"
    private const val BENCHMARK_TASKS = "benchmark-tasks"
    private const val BENCHMARK_CLI = "benchmark-cli"

    fun status(status: ComparisonStatus, locale: Locale = ReportLocale.DEFAULT): String =
        LocalizedMessages.format(BENCHMARK_REPORT, "status.${status.name.lowercase()}", locale)

    fun note(noteKey: String, locale: Locale = ReportLocale.DEFAULT): String {
        val propertyKey = "note." + noteKey.replace(' ', '_')
        return LocalizedMessages.get(BENCHMARK_REPORT, propertyKey, locale) ?: noteKey
    }

    fun summarySeparator(locale: Locale = ReportLocale.DEFAULT): String =
        LocalizedMessages.get(BENCHMARK_REPORT, "summary.separator", locale) ?: ", "

    fun summaryStatusCount(statusLabel: String, count: Int, locale: Locale = ReportLocale.DEFAULT): String =
        LocalizedMessages.format(BENCHMARK_REPORT, "summary.status_count", locale, statusLabel, count)

    fun compareCheckFailed(
        regressions: Int,
        paired: Int,
        fraction: Double,
        allowedPercent: Double,
        locale: Locale = ReportLocale.DEFAULT,
    ): String = LocalizedMessages.format(
        BENCHMARK_REPORT, "compare.check_failed", locale,
        regressions, paired, "%.2f".format(fraction), allowedPercent.toInt(),
    )

    fun compareCheckSkipped(locale: Locale = ReportLocale.DEFAULT): String =
        LocalizedMessages.format(BENCHMARK_REPORT, "compare.check_skipped", locale)

    fun baselineNote(locale: Locale = ReportLocale.DEFAULT): String =
        LocalizedMessages.format(BENCHMARK_REPORT, "baseline.note", locale)

    fun errorJsonNotArray(path: String, locale: Locale = ReportLocale.DEFAULT): String =
        LocalizedMessages.format(BENCHMARK_REPORT, "error.json_not_array", locale, path)

    fun errorSummaryTableNotFound(locale: Locale = ReportLocale.DEFAULT): String =
        LocalizedMessages.format(BENCHMARK_REPORT, "error.summary_table_not_found", locale)

    fun errorNoRowsParsed(locale: Locale = ReportLocale.DEFAULT): String =
        LocalizedMessages.format(BENCHMARK_REPORT, "error.no_rows_parsed", locale)

    fun errorDuplicateKey(key: String, locale: Locale = ReportLocale.DEFAULT): String =
        LocalizedMessages.format(BENCHMARK_REPORT, "error.duplicate_key", locale, key)

    fun errorJsonParseFailed(path: String, locale: Locale = ReportLocale.DEFAULT, message: String): String =
        LocalizedMessages.format(BENCHMARK_REPORT, "error.json_parse_failed", locale, path, message)

    fun html(key: String, locale: Locale = ReportLocale.DEFAULT, vararg args: Any?): String =
        LocalizedMessages.format(BENCHMARK_REPORT, "html.$key", locale, *args)

    fun task(key: String, locale: Locale = ReportLocale.DEFAULT, vararg args: Any?): String =
        LocalizedMessages.format(BENCHMARK_TASKS, key, locale, *args)

    fun benchmarkCli(key: String, locale: Locale = ReportLocale.DEFAULT, vararg args: Any?): String =
        LocalizedMessages.format(BENCHMARK_CLI, key, locale, *args)
}
