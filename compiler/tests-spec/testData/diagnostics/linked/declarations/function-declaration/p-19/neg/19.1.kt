// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: extension functions cannot be called without an explicit or implicit receiver
 */

// TESTCASE NUMBER: 1
fun Int.increment(): Int = this + 1

fun callWithoutReceiver() {
    <!UNRESOLVED_REFERENCE!>increment<!>(1)
}

// TESTCASE NUMBER: 2
fun String.prefix(): String = "p:$this"

fun assumeTopLevelScope() {
    <!UNRESOLVED_REFERENCE!>prefix<!>("abc")
}

// TESTCASE NUMBER: 3
fun Int.describe(): String = toString()

fun wrongReceiverType(value: String) {
    value.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>describe<!>()
}
