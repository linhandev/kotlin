// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, bound-smart-casts -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: non-null member access implies receiver non-null in bound alias set
 */

// TESTCASE NUMBER: 1
class Holder1415(val value: String)

fun case_1(h: Holder1415?) {
    if (h?.value != null) {
        <!DEBUG_INFO_SMARTCAST!>h<!>.hashCode()
    }
}
