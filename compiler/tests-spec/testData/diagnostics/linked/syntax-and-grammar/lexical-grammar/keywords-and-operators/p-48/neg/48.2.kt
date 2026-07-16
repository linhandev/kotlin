// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 48 -> sentence 48
 * NUMBER: 2
 * DESCRIPTION: Space in EQEQ token as = = breaks EQEQ lexeme
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    if (<!VARIABLE_EXPECTED!>1<!> = <!SYNTAX!>=<!><!SYNTAX!><!> 1<!SYNTAX!>)<!> <!UNUSED_LAMBDA_EXPRESSION!>{
        <!RETURN_NOT_ALLOWED!>return<!> "OK"
    }<!>
    return "NOK"
}
