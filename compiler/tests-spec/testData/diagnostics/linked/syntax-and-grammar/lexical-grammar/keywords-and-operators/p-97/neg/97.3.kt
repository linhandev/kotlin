// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 97 -> sentence 97
 * NUMBER: 3
 * DESCRIPTION: Incomplete containment IN without right operand causes parser error
 */

// TESTCASE NUMBER: 1
fun brokenContainmentIn97(value: Int): String = value in<!SYNTAX!><!>

fun case1(): String = "OK"
