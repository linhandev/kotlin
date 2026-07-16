// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 46 -> sentence 46
 * NUMBER: 2
 * DESCRIPTION: Space in EXCL_EQEQ token as ! == breaks EXCL_EQEQ lexeme
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val a = intArrayOf(1)
    val b = intArrayOf(1)
    if (<!TYPE_MISMATCH, TYPE_MISMATCH!>a<!><!SYNTAX!><!> <!DEBUG_INFO_MISSING_UNRESOLVED!>!<!> <!SYNTAX!>==<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>b<!><!SYNTAX!><!SYNTAX!><!>)<!> <!UNUSED_LAMBDA_EXPRESSION!>{
        <!RETURN_NOT_ALLOWED!>return<!> "OK"
    }<!>
    return "NOK"
}
