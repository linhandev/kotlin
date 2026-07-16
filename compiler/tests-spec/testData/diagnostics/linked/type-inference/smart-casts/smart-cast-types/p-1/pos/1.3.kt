// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, smart-cast-types -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: later use of c still smart casts to Any despite declared type Any?
 */

// TESTCASE NUMBER: 1
fun case_1() {
    var a: Any? = null
    if (a == null) return
    var c = a
    <!DEBUG_INFO_SMARTCAST!>c<!>.hashCode()
}
