// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, the-relations-on-types-as-constraints -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: LUB(A, B) produces A <: T, B <: T constraints and infers Number for Int and Double branches
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(flag: Boolean) {
    val e = if (flag) 1 else 2.0
    checkSubtype<Number>(e)
}

// TESTCASE NUMBER: 2
fun case_2(flag: Boolean) {
    val e = if (flag) 1 else "x"
    checkSubtype<Any>(e)
}
