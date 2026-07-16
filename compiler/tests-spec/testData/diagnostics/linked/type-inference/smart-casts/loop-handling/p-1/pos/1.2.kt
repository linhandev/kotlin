// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, loop-handling -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: doWhileAndSmartCasts smart casts after do-while with null check in body
 */

// TESTCASE NUMBER: 1
fun randomBoolean1414(): Boolean = true

fun case_1() {
    var a: Any? = "ok"
    do {
        if (a == null) return
    } while (randomBoolean1414())
    <!DEBUG_INFO_SMARTCAST!>a<!>.hashCode()
}
