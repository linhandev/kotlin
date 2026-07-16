// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions, exhaustive-when-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: when with true and false branches but no subject used as value reports NO_ELSE_IN_WHEN
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x = <!NO_ELSE_IN_WHEN!>when<!> {
        true -> 1
        false -> 2
    }
}
