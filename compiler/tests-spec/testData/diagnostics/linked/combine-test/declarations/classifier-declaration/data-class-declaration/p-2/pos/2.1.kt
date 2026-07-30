// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: for-loop destructuring over data class collection uses componentN
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Pt(val x: Int, val y: Int)

fun case_1(ps: List<Pt>) {
    var s = 0
    for ((x, y) in ps) s += x + y
    checkSubtype<Int>(s)
}
