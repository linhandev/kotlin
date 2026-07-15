/*
 * Copyright (C) 2026 Eazytec Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jetbrains.kotlin.benchmarks.report

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.File
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.regex.Pattern

data class BenchmarkRow(
    val key: String,
    val shortName: String,
    val params: Map<String, String>,
    val mode: String,
    val score: Double,
    val scoreError: Double,
    val scoreUnit: String,
)

enum class ComparisonStatus {
    REGRESSION,
    IMPROVEMENT,
    NEUTRAL,
    BASELINE_ONLY,
    CURRENT_ONLY,
}

data class ComparisonRow(
    val key: String,
    val shortName: String,
    val params: Map<String, String>,
    val mode: String,
    val scoreUnit: String,
    val baselineScore: Double?,
    val baselineError: Double?,
    val currentScore: Double?,
    val currentError: Double?,
    val deltaAbs: Double?,
    val deltaPercent: Double?,
    val status: ComparisonStatus,
    val note: String,
)

data class CompareResult(
    val comparisons: List<ComparisonRow>,
    val failed: Boolean,
    val regressionCount: Int,
    val pairedCount: Int,
    val regressionFractionPercent: Double,
    val checkMessage: String,
)

object BenchmarkCompareSupport {
    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()
    private val paramColRe = Pattern.compile("""\((?<name>[^)]+)\)""")
    private val benchmarkModes = setOf("avgt", "thrpt", "sample", "single", "ss", "avg", "sum")
    private val scoreErrorAttachedRe = Pattern.compile("""^(\d+(?:\.\d+)?)(?:[±](\d+(?:\.\d+)?)|\+/-(\d+(?:\.\d+)?))$""")

    private val useNiBenchmarks = setOf(
        "InferenceExplicitArgumentsCallsBenchmark",
        "InferenceForInApplicableCandidate",
        "InferenceFromArgumentCallsBenchmark",
        "InferenceFromReturnTypeCallsBenchmark",
        "PlusAssignOperatorDesugaringBenchmark",
    )

    fun findLatestMainJson(benchmarksProjectDir: File): File? {
        val base = File(benchmarksProjectDir, "build/reports/benchmarks/main")
        if (!base.isDirectory) return null
        return base.listFiles()?.asSequence()
            ?.mapNotNull { dir -> File(dir, "main.json").takeIf { it.isFile } }
            ?.maxByOrNull { it.lastModified() }
    }

    fun loadBenchmarkJson(path: File, locale: Locale = ReportLocale.DEFAULT): Map<String, BenchmarkRow> {
        val text = try {
            path.readText(StandardCharsets.UTF_8)
        } catch (e: java.nio.charset.MalformedInputException) {
            throw IllegalArgumentException(
                ReportMessages.errorJsonParseFailed(path.path, locale, e.message ?: "")
            )
        }
        val data = JsonParser.parseString(text)
        require(data.isJsonArray) { ReportMessages.errorJsonNotArray(path.path, locale) }
        val out = linkedMapOf<String, BenchmarkRow>()
        data.asJsonArray.forEach { element ->
            if (!element.isJsonObject) return@forEach
            val row = metricFromJsonObject(element.asJsonObject) ?: return@forEach
            if (row.key in out) {
                throw IllegalArgumentException(
                    ReportMessages.errorDuplicateKey(row.key, locale)
                )
            }
            out[row.key] = row
        }
        return out
    }

    fun loadBenchmarkTextTable(text: String, locale: Locale = ReportLocale.DEFAULT): Map<String, BenchmarkRow> {
        val lines = text.lines()
        val start = lines.indexOfFirst { line ->
            line.contains("main summary:") ||
                (line.trimStart().startsWith("Benchmark") && line.contains("(isIR)"))
        }
        require(start >= 0) { ReportMessages.errorSummaryTableNotFound(locale) }

        var headerLine = ""
        for (line in lines.drop(start)) {
            if (line.contains("(isIR)")) {
                headerLine = line.trim()
                break
            }
        }
        require(headerLine.isNotEmpty()) { ReportMessages.errorSummaryTableNotFound(locale) }
        val paramNames = mutableListOf<String>()
        val paramMatcher = paramColRe.matcher(headerLine)
        while (paramMatcher.find()) {
            paramNames.add(paramMatcher.group("name"))
        }

        val dataLines = mutableListOf<String>()
        for (line in lines.drop(start + 1)) {
            if (line.isBlank()) break
            val trimmed = line.trim()
            if (trimmed.startsWith("Benchmark") || line.contains("(isIR)")) continue
            dataLines.add(line)
        }

        val out = linkedMapOf<String, BenchmarkRow>()
        for (raw in dataLines) {
            val parts = raw.trim().split(Regex("\\s+"))
            if (parts.size < 2) continue

            var nameEnd: Int? = null
            for (i in parts.indices) {
                val part = parts[i]
                if (part.isEmpty()) continue
                if (part.endsWith(".benchmark") || (part.endsWith("Benchmark") && '.' in part)) {
                    nameEnd = i + 1
                    break
                }
            }
            val end = nameEnd ?: continue

            val name = parts.subList(0, end).joinToString(" ")
            val remaining = parts.subList(end, parts.size)

            val modeIdx = findModeIndex(remaining) ?: continue
            val metric = parseScoreErrorUnit(remaining, modeIdx) ?: continue
            val (score, err, unit) = metric

            val params = linkedMapOf<String, String>()
            for (i in 0 until modeIdx) {
                if (i < paramNames.size) {
                    params[paramNames[i]] = remaining[i]
                }
            }

            val mode = remaining[modeIdx]
            val short = shortBenchmarkName(name)
            val key = makeKey(short, params)
            if (key in out) {
                throw IllegalArgumentException(
                    ReportMessages.errorDuplicateKey(key, locale)
                )
            }
            out[key] = BenchmarkRow(
                key = key,
                shortName = short,
                params = params,
                mode = mode,
                score = score,
                scoreError = err,
                scoreUnit = unit,
            )
        }
        require(out.isNotEmpty()) { ReportMessages.errorNoRowsParsed(locale) }
        return out
    }

    fun compare(
        baseline: Map<String, BenchmarkRow>,
        current: Map<String, BenchmarkRow>,
        thresholdPercent: Double,
        failIfRegressionExceedsPercent: Double,
        locale: Locale = ReportLocale.DEFAULT,
    ): CompareResult {
        val comparisons = compareMaps(baseline, current, thresholdPercent)
        val counts = summaryCounts(comparisons)
        val regressions = counts[ComparisonStatus.REGRESSION] ?: 0
        val paired = (counts[ComparisonStatus.REGRESSION] ?: 0) +
            (counts[ComparisonStatus.IMPROVEMENT] ?: 0) +
            (counts[ComparisonStatus.NEUTRAL] ?: 0)
        val fraction = if (paired == 0) 0.0 else regressions * 100.0 / paired
        val failed = paired > 0 && fraction > failIfRegressionExceedsPercent
        val checkMessage = if (paired > 0) {
            ReportMessages.compareCheckFailed(regressions, paired, fraction, failIfRegressionExceedsPercent, locale)
        } else {
            ReportMessages.compareCheckSkipped(locale)
        }
        return CompareResult(comparisons, failed, regressions, paired, fraction, checkMessage)
    }

    fun writeReports(
        outputDir: File,
        comparisons: List<ComparisonRow>,
        result: CompareResult,
        meta: Map<String, Any>,
        locale: Locale = ReportLocale.DEFAULT,
    ) {
        outputDir.mkdirs()
        writeJsonReport(File(outputDir, "comparison.json"), comparisons, meta, locale)
        writeHtmlReport(File(outputDir, "comparison.html"), comparisons, meta, locale)
    }

    fun summaryLine(comparisons: List<ComparisonRow>, locale: Locale = ReportLocale.DEFAULT): String {
        val counts = summaryCounts(comparisons)
        val separator = ReportMessages.summarySeparator(locale)
        return counts.entries.sortedBy { it.key.name }.joinToString(separator) { (status, count) ->
            ReportMessages.summaryStatusCount(ReportMessages.status(status, locale), count, locale)
        }
    }

    private fun compareMaps(
        baseline: Map<String, BenchmarkRow>,
        current: Map<String, BenchmarkRow>,
        thresholdPercent: Double,
    ): List<ComparisonRow> {
        val keys = (baseline.keys + current.keys).sorted()
        return keys.map { key ->
            val b = baseline[key]
            val c = current[key]
            when {
                b != null && c != null -> pairedComparison(b, c, thresholdPercent)
                b != null -> ComparisonRow(
                    key, b.shortName, b.params, b.mode, b.scoreUnit,
                    b.score, b.scoreError, null, null, null, null,
                    ComparisonStatus.BASELINE_ONLY, "missing in current",
                )
                else -> {
                    requireNotNull(c)
                    ComparisonRow(
                        key, c.shortName, c.params, c.mode, c.scoreUnit,
                        null, null, c.score, c.scoreError, null, null,
                        ComparisonStatus.CURRENT_ONLY, "missing in baseline",
                    )
                }
            }
        }
    }

    private fun pairedComparison(b: BenchmarkRow, c: BenchmarkRow, thresholdPercent: Double): ComparisonRow {
        val lsb = lowerScoreIsBetter(b)
        val deltaAbs = c.score - b.score
        val deltaPercent = if (b.score == 0.0) null else deltaAbs / b.score * 100.0
        val rel = deltaPercent ?: 0.0
        val status = when {
            lsb && rel > thresholdPercent -> ComparisonStatus.REGRESSION
            lsb && rel < -thresholdPercent -> ComparisonStatus.IMPROVEMENT
            !lsb && rel < -thresholdPercent -> ComparisonStatus.REGRESSION
            !lsb && rel > thresholdPercent -> ComparisonStatus.IMPROVEMENT
            else -> ComparisonStatus.NEUTRAL
        }
        val note = if (lsb) "lower is better" else "higher is better"
        return ComparisonRow(
            b.key, b.shortName, b.params, b.mode, b.scoreUnit,
            b.score, b.scoreError, c.score, c.scoreError, deltaAbs, deltaPercent,
            status, note,
        )
    }

    private fun summaryCounts(comparisons: List<ComparisonRow>): Map<ComparisonStatus, Int> {
        val counts = linkedMapOf<ComparisonStatus, Int>()
        comparisons.forEach { row ->
            counts[row.status] = (counts[row.status] ?: 0) + 1
        }
        return counts
    }

    private fun metricFromJsonObject(o: JsonObject): BenchmarkRow? {
        return try {
            val full = o.get("benchmark")?.asString ?: return null
            val short = shortBenchmarkName(full)
            val params = linkedMapOf<String, String>()
            o.getAsJsonObject("params")?.entrySet()?.forEach { (k, v) -> params[k] = v.asString }
            val pm = o.getAsJsonObject("primaryMetric") ?: return null
            val score = pm.get("score")?.asDouble ?: return null
            val err = pm.get("scoreError")?.asDouble ?: 0.0
            val unit = pm.get("scoreUnit")?.asString ?: ""
            val mode = o.get("mode")?.asString ?: ""
            val key = makeKey(short, params)
            BenchmarkRow(key, short, params, mode, score, err, unit)
        } catch (_: Exception) {
            null
        }
    }

    private fun shortBenchmarkName(full: String): String {
        val withoutSuffix = full.removeSuffix(".benchmark").removeSuffix(".Benchmark")
        val dot = withoutSuffix.lastIndexOf('.')
        return if (dot >= 0) withoutSuffix.substring(dot + 1) else withoutSuffix
    }

    private fun findModeIndex(parts: List<String>): Int? {
        for (i in parts.indices) {
            if (parts[i].lowercase() in benchmarkModes) return i
        }
        return null
    }

    private fun parseScoreErrorUnit(
        remaining: List<String>,
        modeIdx: Int,
    ): Triple<Double, Double, String>? {
        if (modeIdx + 2 >= remaining.size) return null
        val rest = remaining.drop(modeIdx + 2)
        if (rest.isEmpty()) return null

        val scoreToken = rest[0]
        val attached = scoreErrorAttachedRe.matcher(scoreToken)
        if (attached.find()) {
            val err = attached.group(2) ?: attached.group(3) ?: return null
            if (rest.size < 2) return null
            return Triple(attached.group(1)!!.toDouble(), err.toDouble(), rest[1])
        }

        if (rest.size >= 4 && (rest[1] == "±" || rest[1] == "+/-")) {
            return Triple(rest[0].toDouble(), rest[2].toDouble(), rest[3])
        }
        if (rest.size >= 3 && rest[1] != "±" && rest[1] != "+/-") {
            return try {
                Triple(rest[0].toDouble(), rest[1].toDouble(), rest[2])
            } catch (_: NumberFormatException) {
                null
            }
        }
        return null
    }

    private fun normalizeUseNi(value: String?): String =
        if (value == null || value.equals("N/A", ignoreCase = true)) "false" else value

    private fun makeKey(shortName: String, params: Map<String, String>): String {
        val isIr = params["isIR"] ?: "false"
        val size = params["size"] ?: ""
        val parts = mutableListOf(shortName, "isIR=$isIr", "size=$size")
        if (shortName in useNiBenchmarks) {
            val useNi = if (isIr == "true") "true" else normalizeUseNi(params["useNI"])
            parts.add("useNI=$useNi")
        }
        return parts.joinToString("|")
    }

    private fun lowerScoreIsBetter(row: BenchmarkRow): Boolean {
        val unit = row.scoreUnit.lowercase()
        val mode = row.mode.lowercase()
        if (mode == "thrpt" || "ops/" in unit || unit.startsWith("ops/")) return false
        return true
    }

    private fun writeJsonReport(path: File, comparisons: List<ComparisonRow>, meta: Map<String, Any>, locale: Locale) {
        val counts = summaryCounts(comparisons)
        val rows = comparisons.map { c ->
            mapOf(
                "key" to c.key,
                "short_name" to c.shortName,
                "name_zh" to BenchmarkMetadata.displayName(c.shortName, locale),
                "description" to BenchmarkMetadata.description(c.shortName, locale),
                "params" to c.params,
                "mode" to c.mode,
                "score_unit" to c.scoreUnit,
                "baseline_score" to c.baselineScore,
                "baseline_error" to c.baselineError,
                "current_score" to c.currentScore,
                "current_error" to c.currentError,
                "delta_abs" to c.deltaAbs,
                "delta_percent" to c.deltaPercent,
                "status" to ReportMessages.status(c.status, locale),
                "note" to ReportMessages.note(c.note, locale),
            )
        }
        val summary = counts.entries.associate { (status, count) ->
            ReportMessages.status(status, locale) to count
        }
        val payload = mapOf("meta" to meta, "summary" to summary, "benchmarks" to rows)
        path.writeText(gson.toJson(payload), StandardCharsets.UTF_8)
    }

    private fun writeHtmlReport(path: File, comparisons: List<ComparisonRow>, meta: Map<String, Any>, locale: Locale) {
        val summaryHtml = summaryLine(comparisons, locale)
        val threshold = meta["threshold_percent"]
        val failBanner = if (meta["performance_check_failed"] == true) {
            val banner = ReportMessages.html(
                "fail_banner",
                locale,
                htmlEscape(meta["performance_check_message"]?.toString() ?: ""),
            )
            """
  <div style="background:#ffdddd;border:1px solid #c62828;padding:1rem;margin-bottom:1.25rem;max-width:52rem;">
    $banner
  </div>
"""
        } else ""

        val rows = comparisons.joinToString("") { c ->
            val style = when (c.status) {
                ComparisonStatus.REGRESSION -> "background:#ffdddd"
                ComparisonStatus.IMPROVEMENT -> "background:#ddffdd"
                ComparisonStatus.BASELINE_ONLY -> "background:#fff8e1"
                ComparisonStatus.CURRENT_ONLY -> "background:#e3f2fd"
                else -> ""
            }
            val bp = c.baselineScore?.let { "%.6g".format(it) } ?: "—"
            val cp = c.currentScore?.let { "%.6g".format(it) } ?: "—"
            val dp = c.deltaPercent?.let { "%.2f%%".format(it) } ?: "—"
            val da = c.deltaAbs?.let { "%.6g".format(it) } ?: "—"
            val params = c.params.toSortedMap().entries.joinToString(" ") { "${it.key}=${it.value}" }
            val displayName = BenchmarkMetadata.displayName(c.shortName, locale)
            val desc = BenchmarkMetadata.description(c.shortName, locale)
            "<tr style='$style'><td>${htmlEscape(c.shortName)}</td><td>${htmlEscape(displayName)}</td>" +
                "<td class='desc'>${htmlEscape(desc)}</td><td>${htmlEscape(params)}</td>" +
                "<td>${htmlEscape(c.mode)}</td><td>${htmlEscape(c.scoreUnit)}</td>" +
                "<td>$bp</td><td>$cp</td><td>$da</td><td>$dp</td>" +
                "<td><b>${htmlEscape(ReportMessages.status(c.status, locale))}</b></td>" +
                "<td>${htmlEscape(ReportMessages.note(c.note, locale))}</td></tr>"
        }

        val htmlLang = ReportMessages.html("lang", locale)
        val html = """
<!DOCTYPE html>
<html lang="$htmlLang">
<head>
  <meta charset="utf-8"/>
  <title>${htmlEscape(ReportMessages.html("title", locale))}</title>
  <style>
    body { font-family: system-ui, "PingFang SC", "Microsoft YaHei", sans-serif; margin: 1.5rem; }
    table { border-collapse: collapse; width: 100%; }
    th, td { border: 1px solid #ccc; padding: 0.35rem 0.5rem; text-align: left; vertical-align: top; }
    th { background: #f0f0f0; }
    td.desc { max-width: 22rem; font-size: 0.92rem; color: #333; }
    .meta { color: #444; margin-bottom: 1rem; }
    .legend { background: #f8f8f8; border: 1px solid #ddd; padding: 0.75rem 1rem; margin-bottom: 1.25rem; max-width: 52rem; }
    .legend dt { font-weight: 600; margin-top: 0.5rem; }
    .legend dt:first-child { margin-top: 0; }
    .legend dd { margin: 0.15rem 0 0 0; }
  </style>
</head>
<body>
  <h1>${htmlEscape(ReportMessages.html("heading", locale))}</h1>
$failBanner  <div class="meta">
    <div>${htmlEscape(ReportMessages.html("meta.generated", locale, formatGeneratedDisplay(meta["generated"]?.toString(), locale)))}</div>
    <div>${htmlEscape(ReportMessages.html("meta.threshold", locale, threshold))}</div>
    <div>${htmlEscape(ReportMessages.html("meta.baseline", locale, meta["baseline_path"]?.toString()))}</div>
    <div>${htmlEscape(ReportMessages.html("meta.current", locale, meta["current_path"]?.toString()))}</div>
    <div>${htmlEscape(ReportMessages.html("meta.summary", locale, summaryHtml))}</div>
  </div>
  <section class="legend">
    <h2 style="margin-top:0;font-size:1.1rem">${htmlEscape(ReportMessages.html("legend.heading", locale))}</h2>
    <dl>
      <dt>${htmlEscape(ReportMessages.html("legend.name.title", locale))}</dt>
      <dd>${htmlEscape(ReportMessages.html("legend.name.body", locale))}</dd>
      <dt>${htmlEscape(ReportMessages.html("legend.verdict.title", locale))}</dt>
      <dd>${ReportMessages.html("legend.verdict.regression", locale, threshold)}</dd>
      <dd>${ReportMessages.html("legend.verdict.improvement", locale, threshold)}</dd>
      <dd>${ReportMessages.html("legend.verdict.neutral", locale, threshold)}</dd>
      <dd>${ReportMessages.html("legend.verdict.baseline_only", locale)}</dd>
      <dd>${ReportMessages.html("legend.verdict.current_only", locale)}</dd>
      <dt>${htmlEscape(ReportMessages.html("legend.note.title", locale))}</dt>
      <dd>${ReportMessages.html("legend.note.lower", locale)}</dd>
      <dd>${ReportMessages.html("legend.note.higher", locale)}</dd>
      <dd>${ReportMessages.html("legend.note.missing", locale)}</dd>
    </dl>
  </section>
  <table>
    <thead>
      <tr>
        <th>${htmlEscape(ReportMessages.html("table.benchmark", locale))}</th>
        <th>${htmlEscape(ReportMessages.html("table.display_name", locale))}</th>
        <th>${htmlEscape(ReportMessages.html("table.description", locale))}</th>
        <th>${htmlEscape(ReportMessages.html("table.params", locale))}</th>
        <th>${htmlEscape(ReportMessages.html("table.mode", locale))}</th>
        <th>${htmlEscape(ReportMessages.html("table.unit", locale))}</th>
        <th>${htmlEscape(ReportMessages.html("table.baseline", locale))}</th>
        <th>${htmlEscape(ReportMessages.html("table.current", locale))}</th>
        <th>${htmlEscape(ReportMessages.html("table.delta_abs", locale))}</th>
        <th>${htmlEscape(ReportMessages.html("table.delta_pct", locale))}</th>
        <th title="${htmlEscape(ReportMessages.html("table.verdict.title", locale))}">${htmlEscape(ReportMessages.html("table.verdict", locale))}</th>
        <th title="${htmlEscape(ReportMessages.html("table.note.title", locale))}">${htmlEscape(ReportMessages.html("table.note", locale))}</th>
      </tr>
    </thead>
    <tbody>
    $rows
    </tbody>
  </table>
</body>
</html>
""".trimIndent()
        path.writeText(html, StandardCharsets.UTF_8)
    }

    private fun formatGeneratedDisplay(value: String?, locale: Locale): String {
        if (value.isNullOrBlank()) return value.orEmpty()
        return try {
            val instant = Instant.parse(value)
            if (locale.language == "zh") {
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.of("Asia/Shanghai"))
                    .format(instant) + " (北京时间)"
            } else {
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'UTC'")
                    .withZone(ZoneOffset.UTC)
                    .format(instant)
            }
        } catch (_: Exception) {
            value
        }
    }

    private fun htmlEscape(value: String?): String =
        value.orEmpty()
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
}
