// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: fewer destructuring bindings than available components is allowed
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Rgb(val r: Int, val g: Int, val b: Int)

fun case_1(c: Rgb) {
    val r = c.let { (r, g) -> r + g }
    checkSubtype<Int>(r)
}
