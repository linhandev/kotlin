// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 75 -> sentence 75
 * NUMBER: 2
 * DESCRIPTION: Space inside BY token as b y breaks delegated property lexeme
 */

// TESTCASE NUMBER: 1
class BrokenBy75 {
    val token: String <!SYNTAX!>b y lazy<!> <!FUNCTION_DECLARATION_WITH_NO_NAME!><!SYNTAX!><!>{ "OK" }<!>
}

fun case1(): String {
    return "OK"
}
