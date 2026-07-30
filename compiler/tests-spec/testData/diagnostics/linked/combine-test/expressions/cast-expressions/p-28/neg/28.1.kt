// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: is List with concrete type argument is not allowed
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Boolean = x is <!CANNOT_CHECK_FOR_ERASED!>List<String><!>
