// FIR_IDENTICAL
// LANGUAGE: +BreakContinueInInlineLambdas
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, jump-expressions, break-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: break in lambda referring to outer loop reports BREAK_OR_CONTINUE_JUMPS_ACROSS_FUNCTION_BOUNDARY
 */

fun accept(block: () -> Unit) {
    block()
}

// TESTCASE NUMBER: 1
fun case1() {
    for (i in 1..10) {
        accept {
            <!BREAK_OR_CONTINUE_JUMPS_ACROSS_FUNCTION_BOUNDARY!>break<!>
        }
    }
}
