// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -REDUNDANT_PROJECTION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 55 -> sentence 55
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 55 -> sentence 55
 *                declarations, declaration-site-variance-and-use-site-variance -> paragraph 55 -> sentence 55
 * NUMBER: 1
 * DESCRIPTION: covariant out-projected type argument in function parameter accepts subtype argument
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun sum(ns: List<out Number>): Double = ns.sumOf { it.toDouble() }

fun case_1() {
    checkSubtype<Double>(sum(listOf(1, 2)))
}
