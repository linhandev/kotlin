// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: expressions, logical-conjunction-expressions -> paragraph 11 -> sentence 11
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 11 -> sentence 11
 *                type-inference, smart-casts -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: is smart cast in && condition must appear before member access on left-hand side
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Boolean = if (x.<!UNRESOLVED_REFERENCE!>length<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>><!> 0 && x is String) true else false
