/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */
package org.jetbrains.kotlin.benchmarks.report

import com.google.gson.JsonParser
import java.io.File
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.util.regex.Pattern

data class TestDurationResult(
    val totalDuration: Duration,
    val totalMinutes: Double,
    val durations: List<Pair<String, Duration>>,
    val missingReports: List<Pair<String, String>>,
    val unparseableReports: List<Pair<String, String>>,
    val exceeded: Boolean,
    val thresholdMinutes: Double,
) {
    val incompleteReports: List<Pair<String, String>>
        get() = missingReports + unparseableReports
}

object TestDurationSupport {
    const val TESTS_COMMON_NEW_CMD =
        "./gradlew :compiler:tests-common-new:test --tests \"org.jetbrains.kotlin.test.runners.DiagnosticTestGenerated\" --tests \"org.jetbrains.kotlin.test.runners.codegen.IrBlackBoxCodegenTestGenerated\" --rerun-tasks --no-build-cache"
    const val TESTS_SPEC_CMD = "./gradlew :compiler:tests-spec:test --no-configuration-cache"
    const val BENCHMARKS_CMD = "./gradlew :benchmarks:benchmark --no-configuration-cache"

    fun testsCommonNewReportPath(repoRoot: File): File =
        File(repoRoot, "compiler/tests-common-new/build/reports/tests/test/index.html")

    fun testsSpecReportPath(repoRoot: File): File =
        File(repoRoot, "compiler/tests-spec/build/reports/tests/test/index.html")

    fun latestBenchmarkMainJson(repoRoot: File): File? =
        BenchmarkCompareSupport.findLatestMainJson(File(repoRoot, "benchmarks"))

    private val durationBoxRe = Pattern.compile(
        """<div[^>]*(?:class="infoBox"|id="duration")[^>]*(?:class="infoBox"|id="duration")[^>]*>.*?<div class="counter">(.*?)</div>""",
        Pattern.DOTALL,
    )
    private val testsBoxRe = Pattern.compile(
        """<div[^>]*(?:class="infoBox"|id="tests")[^>]*(?:class="infoBox"|id="tests")[^>]*>.*?<div class="counter">(.*?)</div>""",
        Pattern.DOTALL,
    )

    fun check(
        testsCommonNewReport: File?,
        testsSpecReport: File?,
        benchmarkMainJson: File?,
        thresholdMinutes: Double,
    ): TestDurationResult {
        val testConfigs = listOf(
            Triple("tests-common-new", testsCommonNewReport, TESTS_COMMON_NEW_CMD),
            Triple("tests-spec", testsSpecReport, TESTS_SPEC_CMD),
            Triple("benchmarks", benchmarkMainJson, BENCHMARKS_CMD),
        )

        var total = Duration.ZERO
        val durations = mutableListOf<Pair<String, Duration>>()
        val missing = mutableListOf<Pair<String, String>>()
        val unparseable = mutableListOf<Pair<String, String>>()

        for ((name, path, cmd) in testConfigs) {
            if (path == null || !path.isFile) {
                missing += name to cmd
                continue
            }
            val duration = when (name) {
                "benchmarks" -> extractDurationFromBenchmarkJson(path)
                else -> extractDurationFromHtml(path)
            }
            if (duration != null) {
                durations += name to duration
                total += duration
            } else {
                unparseable += name to cmd
            }
        }

        val totalMinutes = total.seconds / 60.0 + total.nano / 60_000_000_000.0
        return TestDurationResult(
            totalDuration = total,
            totalMinutes = totalMinutes,
            durations = durations,
            missingReports = missing,
            unparseableReports = unparseable,
            exceeded = totalMinutes > thresholdMinutes,
            thresholdMinutes = thresholdMinutes,
        )
    }

    private fun extractDurationFromHtml(htmlPath: File): Duration? {
        val html = try {
            htmlPath.readText(StandardCharsets.UTF_8)
        } catch (_: java.nio.charset.MalformedInputException) {
            return null
        }
        val testsCounter = testsBoxRe.matcher(html).let { if (it.find()) it.group(1).trim() else null }
        if (testsCounter == "0" || testsCounter == "-" || testsCounter.isNullOrEmpty()) {
            return null
        }
        val durationCounter = durationBoxRe.matcher(html).let { if (it.find()) it.group(1).trim() else null }
        if (durationCounter == null || durationCounter == "-" || durationCounter.isEmpty()) {
            return null
        }
        return DurationParser.parse(durationCounter)
    }

    private fun extractDurationFromBenchmarkJson(jsonPath: File): Duration? {
        val text = try {
            jsonPath.readText(StandardCharsets.UTF_8)
        } catch (_: java.nio.charset.MalformedInputException) {
            return null
        }
        val data = JsonParser.parseString(text)
        if (!data.isJsonArray) return null
        var total = Duration.ZERO
        data.asJsonArray.forEach { element ->
            if (!element.isJsonObject) return@forEach
            val item = element.asJsonObject
            val forks = (item.get("forks")?.asInt ?: 1).toLong()
            val warmupIterations = (item.get("warmupIterations")?.asInt ?: 0).toLong()
            val warmupTime = DurationParser.parse(item.get("warmupTime")?.asString ?: "0 s") ?: Duration.ZERO
            val measurementIterations = (item.get("measurementIterations")?.asInt ?: 0).toLong()
            val measurementTime = DurationParser.parse(item.get("measurementTime")?.asString ?: "0 s") ?: Duration.ZERO
            val itemDuration = Duration.ofNanos(
                forks * (
                    warmupIterations * warmupTime.toNanos() +
                        measurementIterations * measurementTime.toNanos()
                )
            )
            total += itemDuration
        }
        return total.takeIf { it > Duration.ZERO }
    }
}
