// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: suspend modifier cannot be applied to property getter
 */

// TESTCASE NUMBER: 1
fun case_1() {
    class Box18016 {
        val value: Int
            <!WRONG_MODIFIER_TARGET!>suspend<!> get() = 1
    }
}
