// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 13 -> sentence 13
 *                type-inference, smart-casts -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: sealed interface when branch smart-casts to implementation type
 */

// TESTCASE NUMBER: 1
sealed interface Node
data class Leaf(val v: Int) : Node

fun test(n: Node): Int = when (n) {
    is Leaf -> n.v
}

fun box(): String {
    if (test(Leaf(9)) != 9) return "NOK"
    return "OK"
}
