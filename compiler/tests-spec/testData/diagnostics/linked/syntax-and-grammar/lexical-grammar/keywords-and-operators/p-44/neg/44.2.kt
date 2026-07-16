// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 44 -> sentence 44
 * NUMBER: 2
 * DESCRIPTION: Space in GE token as > = breaks GTEQ lexeme
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    if (3 <!OVERLOAD_RESOLUTION_AMBIGUITY!>><!> <!SYNTAX!>=<!><!SYNTAX!><!> 2<!SYNTAX!>)<!> <!UNUSED_LAMBDA_EXPRESSION!>{
        <!RETURN_NOT_ALLOWED!>return<!> "OK"
    }<!>
    return "NOK"
}
