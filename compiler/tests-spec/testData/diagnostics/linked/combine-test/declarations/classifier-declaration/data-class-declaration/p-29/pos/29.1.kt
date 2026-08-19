// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 29 -> sentence 29
 *                expressions, function-literals, lambda-literals -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: data class componentN via lambda destructuring and explicit componentN share Int result type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Pt(val x: Int, val y: Int)

fun case_1(xs: List<Pt>) {
    checkSubtype<Int>(xs.map { (a, b) -> a * b }.sum())
    checkSubtype<Int>(xs.map { it.component1() * it.component2() }.sum())
}
