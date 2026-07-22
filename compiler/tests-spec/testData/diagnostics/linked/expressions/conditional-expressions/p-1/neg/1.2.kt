// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, conditional-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: if without else branch cannot produce a value (INVALID_IF_AS_EXPRESSION)
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x = <!INVALID_IF_AS_EXPRESSION!>if<!> (true) 1
}
