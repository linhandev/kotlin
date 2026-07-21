// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, finding-optimal-constraint-system-solution -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: implicit pull-up constraint infers T as Int from lower bound of literal argument
 * HELPERS: checkType
 */

fun <T> echo1322(value: T): T = value

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Int>(echo1322(42))
}
