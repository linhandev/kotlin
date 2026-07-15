// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 83 -> sentence 83
 * NUMBER: 3
 * DESCRIPTION: Dangling ELSE without matching IF causes parser error
 */

// TESTCASE NUMBER: 1
fun danglingElse83(): String {
    <!SYNTAX!>else<!> return "OK"
}

fun case1(): String {
    return "OK"
}
