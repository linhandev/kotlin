// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 84 -> sentence 84
 * NUMBER: 3
 * DESCRIPTION: Incomplete when expression WHEN without branches causes parser error
 */

// TESTCASE NUMBER: 1
fun brokenWhenBody84(): String = when (1)<!SYNTAX!><!>

fun case1(): String {
    return "OK"
}
