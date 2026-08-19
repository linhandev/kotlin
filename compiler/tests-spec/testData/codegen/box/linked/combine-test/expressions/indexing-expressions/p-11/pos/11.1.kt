// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 11 -> sentence 11
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: indexing via interface-typed receiver dispatches operator get
 */

// TESTCASE NUMBER: 1
interface Indexed {
    operator fun get(i: Int): Int
}

class Box(val data: IntArray) : Indexed {
    override operator fun get(i: Int): Int = data[i]
}

fun test(x: Indexed): Int = x[0]

fun box(): String {
    if (test(Box(intArrayOf(5))) != 5) return "NOK"
    if (test(Box(intArrayOf(9, 8))) != 9) return "NOK"
    return "OK"
}
