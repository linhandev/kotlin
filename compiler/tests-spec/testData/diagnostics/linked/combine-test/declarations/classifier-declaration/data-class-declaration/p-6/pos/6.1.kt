// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: destructuring fewer components than parameters is allowed
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Rgb(val r: Int, val g: Int, val b: Int)

fun case_1(c: Rgb) {
    val (r, g) = c
    checkSubtype<Int>(r + g)
}
