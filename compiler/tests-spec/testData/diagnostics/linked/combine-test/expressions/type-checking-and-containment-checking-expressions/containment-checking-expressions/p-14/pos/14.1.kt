// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: type-system, type-kinds, type-parameters -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: in operator resolves to generic extension contains with type argument inferred from Box constructor and element type and infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box<T>(val list: List<T>)

operator fun <T> Box<T>.contains(x: T): Boolean = x in list

fun case1() {
    checkSubtype<Boolean>(2 in Box(listOf(1, 2, 3)))
}

fun case2() {
    checkSubtype<Boolean>(4 !in Box(listOf(1, 2, 3)))
}
