// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 10 -> sentence 10
 *                type-inference, smart-casts -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: smart cast from true branch does not apply in else branch of conditional expression
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Int = if (x is String) 1 else x.<!UNRESOLVED_REFERENCE!>length<!>
