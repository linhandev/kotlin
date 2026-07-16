// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, smart-cast-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: smartCastTypeOf applies at stable sink use site after null check
 */

// TESTCASE NUMBER: 1
fun case_1() {
    var a: Any? = null
    if (a == null) return
    val code = <!DEBUG_INFO_SMARTCAST!>a<!>.hashCode()
    println(code)
}
