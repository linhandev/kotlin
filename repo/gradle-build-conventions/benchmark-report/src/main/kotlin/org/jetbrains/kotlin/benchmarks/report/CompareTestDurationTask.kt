/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */
package org.jetbrains.kotlin.benchmarks.report

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class CompareTestDurationTask : DefaultTask() {
    @get:InputFile
    @get:Optional
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val testsCommonNewReport: RegularFileProperty

    @get:InputFile
    @get:Optional
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val testsSpecReport: RegularFileProperty

    @get:InputFile
    @get:Optional
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val benchmarkMainJson: RegularFileProperty

    @get:Input
    abstract val thresholdMinutes: Property<Double>

    @get:Input
    abstract val allowMissingReports: Property<Boolean>

    @get:Input
    abstract val reportLocale: Property<String>

    init {
        thresholdMinutes.convention(180.0)
        allowMissingReports.convention(false)
        reportLocale.convention(ReportLocale.DEFAULT.language)
    }

    @TaskAction
    fun run() {
        val locale = ReportLocale.fromProperty(reportLocale.orNull)
        val repoRoot = project.rootDir
        val result = TestDurationSupport.check(
            testsCommonNewReport = resolveReport(
                testsCommonNewReport,
                TestDurationSupport.testsCommonNewReportPath(repoRoot),
            ),
            testsSpecReport = resolveReport(
                testsSpecReport,
                TestDurationSupport.testsSpecReportPath(repoRoot),
            ),
            benchmarkMainJson = resolveReport(
                benchmarkMainJson,
                TestDurationSupport.latestBenchmarkMainJson(repoRoot),
            ),
            thresholdMinutes = thresholdMinutes.get(),
        )

        logger.lifecycle(ReportMessages.benchmarkCli("compare_duration.section.header", locale))
        val configured = listOf(
            "tests-common-new" to TestDurationSupport.TESTS_COMMON_NEW_CMD,
            "tests-spec" to TestDurationSupport.TESTS_SPEC_CMD,
            "benchmarks" to TestDurationSupport.BENCHMARKS_CMD,
        )
        configured.forEach { (name, _) ->
            val duration = result.durations.find { it.first == name }
            when {
                duration != null -> logger.lifecycle(
                    ReportMessages.benchmarkCli("compare_duration.suite.duration", locale, name, DurationParser.format(duration.second)),
                )
                result.missingReports.any { it.first == name } -> logger.lifecycle(
                    ReportMessages.benchmarkCli("compare_duration.suite.missing_report", locale, name),
                )
                else -> logger.lifecycle(
                    ReportMessages.benchmarkCli("compare_duration.suite.unparseable", locale, name),
                )
            }
        }

        if (result.incompleteReports.isNotEmpty()) {
            logger.lifecycle("")
            logger.lifecycle(ReportMessages.benchmarkCli("compare_duration.missing_reports.warning", locale))
            logger.lifecycle(ReportMessages.benchmarkCli("compare_duration.missing_reports.hint", locale))
            result.incompleteReports.forEach { (_, cmd) -> logger.lifecycle("  $cmd") }
        }

        logger.lifecycle("")
        logger.lifecycle(ReportMessages.benchmarkCli("compare_duration.summary.header", locale))
        logger.lifecycle(
            ReportMessages.benchmarkCli(
                "compare_duration.total",
                locale,
                DurationParser.formatWithMinutes(result.totalDuration, locale),
            ),
        )
        logger.lifecycle(ReportMessages.benchmarkCli("compare_duration.threshold", locale, result.thresholdMinutes))

        if (result.incompleteReports.isNotEmpty() && !allowMissingReports.get()) {
            val message = ReportMessages.benchmarkCli("compare_duration.error.missing_reports", locale)
            logger.lifecycle(message)
            throw GradleException(message)
        }

        if (result.exceeded) {
            val message = ReportMessages.benchmarkCli(
                "compare_duration.exceeded",
                locale,
                DurationParser.formatWithMinutes(result.totalDuration, locale),
                result.thresholdMinutes,
            )
            logger.lifecycle(message)
            throw GradleException(message)
        }
        logger.lifecycle(ReportMessages.benchmarkCli("compare_duration.within_threshold", locale))
    }

    private fun resolveReport(property: RegularFileProperty, default: File?): File? {
        property.orNull?.asFile?.takeIf { it.isFile }?.let { return it }
        return default?.takeIf { it.isFile }
    }
}
