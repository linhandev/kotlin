// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 92 -> sentence 92
 * NUMBER: 2
 * DESCRIPTION: Space inside RETURN token as re turn breaks return statement lexeme
 */

// TESTCASE NUMBER: 1
fun brokenReturn92(): String {
    <!UNRESOLVED_REFERENCE!>re<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>turn<!> "OK"
<!NO_RETURN_IN_FUNCTION_WITH_BLOCK_BODY!>}<!>

fun case1(): String = "OK"
