// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 99 -> sentence 99
 * NUMBER: 2
 * DESCRIPTION: Space inside NOT_IN token as ! in breaks !in lexeme
 */

// TESTCASE NUMBER: 1
fun brokenNotInSpace99(value: Int): String {
    return <!TYPE_MISMATCH!>value<!> <!SYNTAX!>! in 1..3<!>
}

fun case1(): String = "OK"
