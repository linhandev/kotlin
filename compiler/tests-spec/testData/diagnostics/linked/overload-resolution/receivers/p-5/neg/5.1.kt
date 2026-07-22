// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, receivers -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: member extension on Y declared in X requires implicit dispatch receiver of type X
 */

// TESTCASE NUMBER: 1
interface Y1105

class X1105 : Y1105 {
    fun Y1105.foo(): String = "OK"
}

fun case_1(y: Y1105): String = y.<!UNRESOLVED_REFERENCE!>foo<!>()
