// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 89 -> sentence 89
 * NUMBER: 2
 * DESCRIPTION: Space inside DO token as d o breaks do-while lexeme
 */

// TESTCASE NUMBER: 1
fun brokenDo89(): String {
    <!UNRESOLVED_REFERENCE!>d<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>o<!> {
        <!RETURN_NOT_ALLOWED!>return<!> "OK"
    }<!SYNTAX!><!> while (false)
    return "NOK"
<!NO_RETURN_IN_FUNCTION_WITH_BLOCK_BODY!>}<!>

fun case1(): String = "OK"
