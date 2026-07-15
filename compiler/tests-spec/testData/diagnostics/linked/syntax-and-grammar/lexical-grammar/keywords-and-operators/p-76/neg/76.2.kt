// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 76 -> sentence 76
 * NUMBER: 2
 * DESCRIPTION: Space inside COMPANION token as comp anion breaks companion object lexeme
 */

// TESTCASE NUMBER: 1
class BrokenCompanion76 {
    <!SYNTAX!>comp<!> <!SYNTAX!>anion<!> object<!SYNTAX!><!> {}
}

fun case1(): String {
    return "OK"
}
