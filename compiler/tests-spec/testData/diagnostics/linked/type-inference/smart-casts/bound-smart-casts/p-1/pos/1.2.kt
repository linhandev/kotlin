// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, bound-smart-casts -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: alias set with three stable copies propagates smart cast to all members
 * UNEXPECTED BEHAVIOUR
 */

// TESTCASE NUMBER: 1
fun case_1() {
    var a: Any? = "ok"
    val b = a
    val c = a
    if (a is String) {
        b.<!UNRESOLVED_REFERENCE!>length<!>
        c.<!UNRESOLVED_REFERENCE!>length<!>
    }
}
