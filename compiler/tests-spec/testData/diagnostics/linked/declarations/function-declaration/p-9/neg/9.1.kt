// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: cannot assume later arguments are evaluated before earlier ones
 */

// TESTCASE NUMBER: 1
fun record(a: Int, b: Int) {}

fun assumeReverseEvaluationOrder() {
    var value: Int
    record(<!UNINITIALIZED_VARIABLE!>value<!>, run { value = 1; 0 })
}
