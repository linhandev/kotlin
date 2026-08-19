// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 53 -> sentence 53
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 53 -> sentence 53
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 53 -> sentence 53
 * NUMBER: 1
 * DESCRIPTION: non-reified type parameter cannot be used for runtime type check
 */

// TESTCASE NUMBER: 1
fun <T> bad(x: Any): Boolean = x is <!CANNOT_CHECK_FOR_ERASED!>T<!>
