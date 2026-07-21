// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: block body, expression body, and default parameter value in function declaration compile successfully
 */

// TESTCASE NUMBER: 1
fun block(): String {
    return "block"
}

// TESTCASE NUMBER: 2
fun expression(): String = "expression"

// TESTCASE NUMBER: 3
fun withDefault(x: Int = 1): Int = x
