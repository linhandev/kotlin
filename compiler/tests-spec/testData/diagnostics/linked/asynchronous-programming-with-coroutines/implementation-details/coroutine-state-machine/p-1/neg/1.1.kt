// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, coroutine-state-machine -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: suspend modifier cannot be applied to property setter for state machine colouring
 */

// TESTCASE NUMBER: 1
fun case_1() {
    class Holder18105 {
        var value: Int = 0
            <!WRONG_MODIFIER_TARGET!>suspend<!> set(v) {
                value = v
            }
    }
}
