// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 94 -> sentence 94
 * NUMBER: 3
 * DESCRIPTION: BREAK token outside loop causes compile error
 */

// TESTCASE NUMBER: 1
fun brokenBreakLoop94(): String {
    <!BREAK_OR_CONTINUE_OUTSIDE_A_LOOP!>break<!>
<!NO_RETURN_IN_FUNCTION_WITH_BLOCK_BODY!>}<!>

fun case1(): String = "OK"
