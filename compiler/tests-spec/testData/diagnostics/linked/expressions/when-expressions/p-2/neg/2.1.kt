// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: else branch before another when entry reports ELSE_MISPLACED_IN_WHEN and makes later branch unreachable
 */

// TESTCASE NUMBER: 1
fun case1(): Int {
    var z = 0
    when (2) {
        1 -> z++
        <!ELSE_MISPLACED_IN_WHEN!>else<!> -> z = -1
        <!UNREACHABLE_CODE!>3 -> z++<!>
    }
    return z
}
