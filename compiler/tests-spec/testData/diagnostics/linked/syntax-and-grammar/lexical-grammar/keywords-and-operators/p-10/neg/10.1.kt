// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: MULT token without right-hand operand causes parser error
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    <!UNREACHABLE_CODE!>val x =<!> 1 <!OVERLOAD_RESOLUTION_AMBIGUITY, UNREACHABLE_CODE!>*<!>
    return "OK"
}
