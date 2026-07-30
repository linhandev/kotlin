// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 18 -> sentence 18
 *                expressions, when-expressions -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: when branch with in operator and custom contains infers String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box(val list: List<Int>)
operator fun Box.contains(x: Int): Boolean = x in list

fun case1(x: Int): String = when {
    x in Box(listOf(1, 2, 3)) -> "found"
    else -> "not found"
}

fun case2() {
    checkSubtype<String>(case1(2))
    checkSubtype<String>(case1(9))
}
