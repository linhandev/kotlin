// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 22 -> sentence 22
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: overload resolution selects Int overload with default when all arguments are omitted
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun f(x: Int = 1): Int = x

fun f(x: String): String = x

fun case_1() {
    checkSubtype<Int>(f())
}
