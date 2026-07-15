// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 93 -> sentence 93
 * NUMBER: 1
 * DESCRIPTION: Standalone CONTINUE token as statement causes compile error
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    var step = 0
    <!BREAK_OR_CONTINUE_OUTSIDE_A_LOOP!>continue<!>
    return "OK"
}
