/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */
package org.jetbrains.kotlin.benchmarks.report

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.time.Instant

abstract class CompareBenchmarkResultsTask : DefaultTask() {
    @get:InputFile
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val baselineFile: RegularFileProperty

    @get:InputFile
    @get:Optional
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val currentFile: RegularFileProperty

    @get:InputFile
    @get:Optional
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val currentTextFile: RegularFileProperty

    @get:OutputDirectory
    abstract val reportOutputDirectory: DirectoryProperty

    @get:Input
    abstract val thresholdPercent: Property<Double>

    @get:Input
    abstract val failIfRegressionExceedsPercent: Property<Double>

    @get:Input
    abstract val reportLocale: Property<String>

    init {
        thresholdPercent.convention(5.0)
        failIfRegressionExceedsPercent.convention(5.0)
        reportLocale.convention(ReportLocale.DEFAULT.language)
    }

    @TaskAction
    fun run() {
        val locale = ReportLocale.fromProperty(reportLocale.orNull)
        val baselinePath = baselineFile.get().asFile
        if (!baselinePath.isFile) {
            val message = ReportMessages.task("error.baseline_not_found", locale, baselinePath)
            logger.lifecycle(message)
            throw GradleException(message)
        }

        if (currentFile.isPresent && currentTextFile.isPresent) {
            val message = ReportMessages.task("error.both_current_sources", locale)
            logger.lifecycle(message)
            throw GradleException(message)
        }

        val (current, currentLabel) = try {
            resolveCurrent(locale)
        } catch (e: GradleException) {
            logger.lifecycle(e.message.orEmpty())
            throw e
        }

        logger.lifecycle(ReportMessages.task("log.baseline_file", locale, baselinePath))
        val baseline = BenchmarkCompareSupport.loadBenchmarkJson(baselinePath, locale)
        logger.lifecycle(ReportMessages.task("log.current_file", locale, currentLabel))
        logger.lifecycle(ReportMessages.task("log.compare_counts", locale, baseline.size, current.size))

        val baselineOnly = baseline.keys - current.keys
        val currentOnly = current.keys - baseline.keys
        if (baselineOnly.isNotEmpty() || currentOnly.isNotEmpty()) {
            logger.warn(
                ReportMessages.task("log.keys_mismatch", locale, baselineOnly.size, currentOnly.size),
            )
        }

        val result = BenchmarkCompareSupport.compare(
            baseline = baseline,
            current = current,
            thresholdPercent = thresholdPercent.get(),
            failIfRegressionExceedsPercent = failIfRegressionExceedsPercent.get(),
            locale = locale,
        )

        val meta = linkedMapOf<String, Any>(
            "generated" to Instant.now().toString(),
            "threshold_percent" to thresholdPercent.get(),
            "fail_if_regression_exceeds_percent" to failIfRegressionExceedsPercent.get(),
            "baseline_path" to baselinePath.path,
            "baseline_note" to ReportMessages.baselineNote(locale),
            "current_path" to currentLabel,
            "performance_check_failed" to result.failed,
            "performance_check_message" to result.checkMessage,
            "regression_count" to result.regressionCount,
            "paired_benchmark_count" to result.pairedCount,
            "regression_fraction_percent" to result.regressionFractionPercent,
        )

        val outDir = reportOutputDirectory.get().asFile
        BenchmarkCompareSupport.writeReports(outDir, result.comparisons, result, meta, locale)

        logger.lifecycle(ReportMessages.task("log.report_written", locale, outDir))
        logger.lifecycle(ReportMessages.task("log.summary", locale, BenchmarkCompareSupport.summaryLine(result.comparisons, locale)))
        if (result.pairedCount > 0) {
            logger.lifecycle(
                ReportMessages.task(
                    "log.regression_fraction",
                    locale,
                    "%.2f".format(result.regressionFractionPercent),
                    failIfRegressionExceedsPercent.get().toInt(),
                ),
            )
        }
        if (result.failed) {
            val message = ReportMessages.task("error.compare_failed", locale, result.checkMessage, outDir.path)
            logger.lifecycle(message)
            throw GradleException(message)
        }
    }

    private fun resolveCurrent(locale: java.util.Locale): Pair<Map<String, BenchmarkRow>, String> = when {
        currentFile.isPresent -> {
            val path = currentFile.get().asFile
            if (!path.isFile) {
                throw GradleException(ReportMessages.task("error.current_json_not_found", locale, path))
            }
            BenchmarkCompareSupport.loadBenchmarkJson(path, locale) to path.path
        }
        currentTextFile.isPresent -> {
            val path = currentTextFile.get().asFile
            if (!path.isFile) {
                throw GradleException(ReportMessages.task("error.current_log_not_found", locale, path))
            }
            try {
                BenchmarkCompareSupport.loadBenchmarkTextTable(path.readText(), locale) to path.path
            } catch (e: IllegalArgumentException) {
                throw GradleException(ReportMessages.task("error.parse_summary_failed", locale, path, e.message))
            }
        }
        else -> {
            val auto = BenchmarkCompareSupport.findLatestMainJson(project.projectDir)
            if (auto != null) {
                BenchmarkCompareSupport.loadBenchmarkJson(auto, locale) to auto.path
            } else {
                val log = File(project.projectDir, "baseline/last-benchmark-console.log")
                if (!log.isFile) {
                    throw GradleException(
                        ReportMessages.task("error.no_current_data", locale, log.path),
                    )
                }
                try {
                    BenchmarkCompareSupport.loadBenchmarkTextTable(log.readText(), locale) to log.path
                } catch (e: IllegalArgumentException) {
                    throw GradleException(ReportMessages.task("error.parse_summary_failed", locale, log.path, e.message))
                }
            }
        }
    }
}
