// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 29 -> sentence 29
 *                declarations, declarations-with-type-parameters -> paragraph 29 -> sentence 29
 *                type-system, introduction-1 -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: non-runtime-available type in when expression is-check
 */

// TESTCASE NUMBER: 1
fun <T> case_1(value: Any?): String = when (value) {
    is <!CANNOT_CHECK_FOR_ERASED!>T<!> -> "yes"
    else -> "no"
}
