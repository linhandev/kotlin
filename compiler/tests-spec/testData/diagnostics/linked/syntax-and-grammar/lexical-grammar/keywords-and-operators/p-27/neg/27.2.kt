// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 27 -> sentence 27
 * NUMBER: 2
 * DESCRIPTION: DIV_ASSIGNMENT token without right-hand operand causes parser error
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    var x = 1
    x <!OVERLOAD_RESOLUTION_AMBIGUITY, UNREACHABLE_CODE!>/=<!>
    return "OK"
}
