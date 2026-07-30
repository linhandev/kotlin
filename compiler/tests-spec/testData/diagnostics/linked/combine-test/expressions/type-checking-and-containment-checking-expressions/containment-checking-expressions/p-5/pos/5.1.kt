// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: in operator resolves to class member contains function and infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box(val list: List<Int>) {
    operator fun contains(x: Int): Boolean = x in list
}

fun case1() {
    checkSubtype<Boolean>(2 in Box(listOf(1, 2, 3)))
}
