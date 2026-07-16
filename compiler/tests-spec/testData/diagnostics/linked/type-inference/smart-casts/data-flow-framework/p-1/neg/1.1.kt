// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, data-flow-framework -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: join of incompatible branch facts does not retain narrowed type after if-else
 */

// TESTCASE NUMBER: 1
fun case_1(flag: Boolean, x: Any) {
    if (flag) {
        if (x is Int) {
        }
    } else {
        if (x is String) {
        }
    }
    x.<!UNRESOLVED_REFERENCE!>length<!>
}
