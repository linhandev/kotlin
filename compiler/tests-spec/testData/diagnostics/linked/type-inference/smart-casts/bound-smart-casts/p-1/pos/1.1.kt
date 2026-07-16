// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, bound-smart-casts -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: bound smart cast propagates is check on b to stable alias a
 * UNEXPECTED BEHAVIOUR
 */

// TESTCASE NUMBER: 1
fun case_1(aInit: Any?) {
    var a: Any? = aInit
    val b = a
    if (b is String) {
        a.<!UNRESOLVED_REFERENCE!>length<!>
    }
}
