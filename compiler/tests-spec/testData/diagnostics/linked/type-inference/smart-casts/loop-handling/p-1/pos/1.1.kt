// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, loop-handling -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: breakFromInfiniteLoop smart casts after while true with null check and break
 */

// TESTCASE NUMBER: 1
fun randomBoolean1414(): Boolean = true

fun case_1() {
    var a: Any? = "ok"
    while (true) {
        if (a == null) return
        if (randomBoolean1414()) break
    }
    <!DEBUG_INFO_SMARTCAST!>a<!>.hashCode()
}
