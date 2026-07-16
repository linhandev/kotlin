// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, bound-smart-casts -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: reassignment breaks must-alias so is check on b does not smart cast a
 */

// TESTCASE NUMBER: 1
fun case_1() {
    var a: Any? = "ok"
    val b = a
    a = null
    if (b is String) {
        a.<!UNRESOLVED_REFERENCE!>length<!>
    }
}
