// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions, exhaustive-when-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: when (true) { true -> 1 } missing false branch used as value reports NO_ELSE_IN_WHEN
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x = <!NO_ELSE_IN_WHEN!>when<!> (true) {
        true -> 1
    }
}
