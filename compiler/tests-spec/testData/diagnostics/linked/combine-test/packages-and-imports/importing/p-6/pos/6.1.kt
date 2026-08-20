// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 6 -> sentence 6
 *                declarations, property-declaration -> paragraph 6 -> sentence 6
 *                expressions, call-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: imported top-level property used as call argument by short name type inference
 * HELPERS: checkType
 */
import kotlin.math.PI

fun scale56006(x: Double): Double = x * 2

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Double>(scale56006(PI))
}
