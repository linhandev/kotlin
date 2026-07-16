// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, loop-handling -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: doWhileAndSmartCasts2 smart casts after do-while with a == null condition
 */

// TESTCASE NUMBER: 1
fun case_1() {
    var a: Any? = null
    do {
    } while (a == null)
    <!DEBUG_INFO_SMARTCAST!>a<!>.hashCode()
}
