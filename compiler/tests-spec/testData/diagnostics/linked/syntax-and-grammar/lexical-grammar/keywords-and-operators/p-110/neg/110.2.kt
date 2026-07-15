// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 110 -> sentence 110
 * NUMBER: 2
 * DESCRIPTION: Space inside INNER token as in ner breaks inner declaration lexeme
 */

// TESTCASE NUMBER: 1
class BrokenInner110 {
    in <!SYNTAX!>ner<!> class Inner110
}

fun case1(): String = "OK"
