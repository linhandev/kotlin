// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, the-relations-on-types-as-constraints -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: constraint-system LUB is sound upper bound for unconstrained branch type variables
 * HELPERS: checkType
 */

fun <A, B> lubConstraint1323(flag: Boolean, a: A, b: B): Any? {
    return if (flag) a else b
}

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Any?>(lubConstraint1323(true, 1, "x"))
}
