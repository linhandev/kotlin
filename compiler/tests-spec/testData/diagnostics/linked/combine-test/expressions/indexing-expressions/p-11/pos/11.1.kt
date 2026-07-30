// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 11 -> sentence 11
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: interface-typed index read infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Indexed {
    operator fun get(i: Int): Int
}

class Box(val data: IntArray) : Indexed {
    override operator fun get(i: Int): Int = data[i]
}

fun case1() {
    val x: Indexed = Box(intArrayOf(5))
    checkSubtype<Int>(x[0])
}
