// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, type-constraint-definition -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: implicit constraint T <: kotlin.Any? allows nullable and non-null types
 * HELPERS: checkType
 */

fun <T> echo131(t: T): T = t

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Any?>(echo131(null))
}

// TESTCASE NUMBER: 2
fun case_2() {
    checkSubtype<Int>(echo131(42))
}
