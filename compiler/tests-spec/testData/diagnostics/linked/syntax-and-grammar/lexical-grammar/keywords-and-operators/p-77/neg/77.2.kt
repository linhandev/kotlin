// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 77 -> sentence 77
 * NUMBER: 2
 * DESCRIPTION: Space inside INIT token as in it breaks initializer block lexeme
 */

// TESTCASE NUMBER: 1
class BrokenInit77 {
    in <!SYNTAX!>it<!> <!FUNCTION_DECLARATION_WITH_NO_NAME!><!SYNTAX!><!>{
        val x = 1
    }<!>
}

fun case1(): String {
    return "OK"
}
