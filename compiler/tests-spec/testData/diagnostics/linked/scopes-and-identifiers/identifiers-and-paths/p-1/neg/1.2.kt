// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, identifiers-and-paths -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: h.missing on Holder642 reports UNRESOLVED_REFERENCE
 */

// TESTCASE NUMBER: 1
class Holder642(val value: Int)

fun case1(h: Holder642): Int = h.<!UNRESOLVED_REFERENCE!>missing<!>
