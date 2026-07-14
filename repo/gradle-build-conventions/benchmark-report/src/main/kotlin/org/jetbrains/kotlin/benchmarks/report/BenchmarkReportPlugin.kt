/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */
package org.jetbrains.kotlin.benchmarks.report

import org.gradle.api.Plugin
import org.gradle.api.Project

class BenchmarkReportPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        if (project.path == ":benchmarks") {
            registerCompareTask(project)
        }
        if (project === project.rootProject) {
            registerCompareTestDurationTask(project)
        }
    }

    private fun reportLocale(project: Project) =
        ReportLocale.fromProperty(project.findProperty(ReportLocale.PROPERTY_NAME)?.toString())

    private fun configureReportLocale(task: CompareBenchmarkResultsTask, project: Project) {
        project.findProperty(ReportLocale.PROPERTY_NAME)?.toString()?.trim()?.takeIf { it.isNotEmpty() }?.let {
            task.reportLocale.set(it)
        }
    }

    private fun configureReportLocale(task: CompareTestDurationTask, project: Project) {
        project.findProperty(ReportLocale.PROPERTY_NAME)?.toString()?.trim()?.takeIf { it.isNotEmpty() }?.let {
            task.reportLocale.set(it)
        }
    }

    private fun registerCompareTask(project: Project) {
        val locale = reportLocale(project)
        project.tasks.register("compareBenchmarkResults", CompareBenchmarkResultsTask::class.java) {
            group = "benchmark"
            description = ReportMessages.task("task.compare_benchmark.description", locale)

            baselineFile.set(project.file("baseline/benchmark-baseline.json"))
            reportOutputDirectory.set(project.file("baseline/reports"))

            // Re-run every execution (main.json path is resolved at runtime; do not rely on Gradle up-to-date)
            outputs.upToDateWhen { false }

            val currentProp = project.findProperty("benchmarkCurrent")?.toString()?.trim()
            val currentTextProp = project.findProperty("benchmarkCurrentText")?.toString()?.trim()

            if (!currentProp.isNullOrEmpty()) {
                currentFile.set(project.file(currentProp))
            }
            if (!currentTextProp.isNullOrEmpty()) {
                currentTextFile.set(project.file(currentTextProp))
            }

            project.findProperty("benchmarkThresholdPercent")?.toString()?.toDoubleOrNull()?.let {
                thresholdPercent.set(it)
            }
            project.findProperty("benchmarkFailIfRegressionExceedsPercent")?.toString()?.toDoubleOrNull()?.let {
                failIfRegressionExceedsPercent.set(it)
            }
            configureReportLocale(this, project)
        }
    }

    private fun registerCompareTestDurationTask(project: Project) {
        val locale = reportLocale(project)
        project.tasks.register("compareTestDuration", CompareTestDurationTask::class.java) {
            group = "verification"
            description = ReportMessages.benchmarkCli("compare_duration.task.description", locale)

            TestDurationSupport.testsCommonNewReportPath(project.rootDir)
                .takeIf { it.isFile }
                ?.let { testsCommonNewReport.set(it) }
            TestDurationSupport.testsSpecReportPath(project.rootDir)
                .takeIf { it.isFile }
                ?.let { testsSpecReport.set(it) }
            TestDurationSupport.latestBenchmarkMainJson(project.rootDir)?.let { benchmarkMainJson.set(it) }

            outputs.upToDateWhen { false }

            project.findProperty("testDurationThresholdMinutes")?.toString()?.toDoubleOrNull()?.let {
                thresholdMinutes.set(it)
            }
            project.findProperty("testDurationAllowMissingReports")?.toString()?.trim()?.takeIf { it.isNotEmpty() }?.let {
                allowMissingReports.set(it.toBoolean())
            }
            configureReportLocale(this, project)
        }
    }
}
