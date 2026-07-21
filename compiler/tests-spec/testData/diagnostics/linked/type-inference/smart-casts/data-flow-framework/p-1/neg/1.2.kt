// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, data-flow-framework -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: assume(x !is T) transfer excludes T in false branch
 */

// TESTCASE NUMBER: 1
fun case_1(x: Any) {
    if (x is String) {
        <!DEBUG_INFO_SMARTCAST!>x<!>.length
    } else {
        val s: String = <!TYPE_MISMATCH!>x<!>
    }
}
