// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 91 -> sentence 91
 * NUMBER: 2
 * DESCRIPTION: Space inside THROW token as thr ow breaks throw expression lexeme
 */

// TESTCASE NUMBER: 1
fun brokenThrow91(): String {
    <!UNRESOLVED_REFERENCE!>thr<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>ow<!> RuntimeException()
    return "OK"
}

fun case1(): String = "OK"
