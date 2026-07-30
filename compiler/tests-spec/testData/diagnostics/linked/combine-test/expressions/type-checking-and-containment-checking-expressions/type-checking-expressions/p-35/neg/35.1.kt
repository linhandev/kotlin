// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 35 -> sentence 35
 * PRIMARY LINKS: type-system, type-kinds, type-parameters -> paragraph 35 -> sentence 35
 *                expressions, function-literals, lambda-literals -> paragraph 35 -> sentence 35
 * NUMBER: 1
 * DESCRIPTION: non-reified type parameter T used in is-check inside lambda body reports CANNOT_CHECK_FOR_ERASED
 */

// TESTCASE NUMBER: 1
fun <T> case_1(value: Any?): Boolean {
    val f: (Any?) -> Boolean = { it is <!CANNOT_CHECK_FOR_ERASED!>T<!> }
    return f(value)
}
