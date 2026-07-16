// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 82 -> sentence 82
 * NUMBER: 3
 * DESCRIPTION: Incomplete if expression IF without condition causes parser error
 */

// TESTCASE NUMBER: 1
fun brokenIfCond82(): String = if<!SYNTAX!><!> "OK" else "NOK"

fun case1(): String {
    return "OK"
}
