// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 98 -> sentence 98
 * NUMBER: 2
 * DESCRIPTION: Space inside NOT_IS token as ! is breaks !is lexeme
 */

// TESTCASE NUMBER: 1
fun brokenNotIs98(value: Any): String {
    return <!TYPE_MISMATCH!>value<!> <!SYNTAX!>! is String<!>
}

fun case1(): String = "OK"
