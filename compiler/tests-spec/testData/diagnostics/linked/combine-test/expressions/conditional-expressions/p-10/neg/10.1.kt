// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 1 -> sentence 1
 *                type-inference, smart-casts -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: smart cast does not apply outside the branch where type was narrowed
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Int { if (x is String) return x.length; return x.<!UNRESOLVED_REFERENCE!>length<!> }