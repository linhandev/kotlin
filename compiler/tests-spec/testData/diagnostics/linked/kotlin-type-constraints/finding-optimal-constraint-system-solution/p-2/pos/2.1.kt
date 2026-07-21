// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, finding-optimal-constraint-system-solution -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: push-down on conditional LUB result infers Number for if (flag) a else b
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(flag: Boolean) {
    val a = 1
    val b = 2.0
    val e = if (flag) a else b
    checkSubtype<Number>(e)
}
