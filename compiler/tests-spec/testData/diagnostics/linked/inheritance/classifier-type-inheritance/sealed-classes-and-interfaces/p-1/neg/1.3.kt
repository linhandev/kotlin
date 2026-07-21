// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: when (c: Color512) with Red and Green missing Blue512 reports NO_ELSE_IN_WHEN
 */

sealed class Color512 {
    object Red512 : Color512()
    object Green512 : Color512()
    object Blue512 : Color512()
}

// TESTCASE NUMBER: 1
fun case1(c: Color512): Int {
    return <!NO_ELSE_IN_WHEN!>when<!>(c) {
        Color512.Red512 -> 1
        Color512.Green512 -> 2
    }
}
