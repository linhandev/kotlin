// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 12 -> sentence 12
 *                type-inference, smart-casts, smart-cast-sink-stability -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: smart cast is invalidated after reassignment to var local in conditional expression branch
 */

// TESTCASE NUMBER: 1
fun test(x0: Any): Int {
    var x = x0
    return if (x is String) { x = 1; x.<!UNRESOLVED_REFERENCE!>length<!> } else -1
}
