// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: data class componentN supports lambda destructuring
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Pt(val x: Int, val y: Int)

fun case_1(xs: List<Pt>) {
    val r = xs.sumOf { (x, y) -> x + y }
    checkSubtype<Int>(r)
}
