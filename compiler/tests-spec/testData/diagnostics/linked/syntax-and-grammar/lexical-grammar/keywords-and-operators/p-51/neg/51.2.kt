// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 51 -> sentence 51
 * NUMBER: 2
 * DESCRIPTION: Space in RETURN_AT token as return @loop breaks RETURN_AT lexeme
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    loop@ for (i in 1..3) {
        <!NOT_A_FUNCTION_LABEL!>return<!SYNTAX!><!> @loop "OK"<!>
    }
    return "NOK"
}
