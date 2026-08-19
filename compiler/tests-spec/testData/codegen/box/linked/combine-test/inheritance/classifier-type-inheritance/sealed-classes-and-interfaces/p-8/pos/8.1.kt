// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 8 -> sentence 8
 *                type-inference, smart-casts -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: more specific sealed subclasses should be matched before intermediate types
 */

// TESTCASE NUMBER: 1
sealed class Node
sealed class Branch : Node()
data class Leaf(val v: Int) : Node()
data class Twig(val v: Int) : Branch()

fun test(n: Node): Int = when (n) {
    is Leaf -> n.v
    is Twig -> n.v
    is Branch -> 0
}

fun box(): String {
    if (test(Leaf(1)) != 1) return "NOK"
    if (test(Twig(2)) != 2) return "NOK"
    return "OK"
}
