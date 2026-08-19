// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, type-kinds, type-parameters -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: in operator resolves to generic extension contains with Number upper bound and infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box<T : Number>(val threshold: T)

operator fun <T : Number> Box<T>.contains(x: T): Boolean = x.toDouble() >= threshold.toDouble()

fun case1() {
    checkSubtype<Boolean>(5 in Box(3))
}

fun case2() {
    checkSubtype<Boolean>(1 !in Box(3))
}
