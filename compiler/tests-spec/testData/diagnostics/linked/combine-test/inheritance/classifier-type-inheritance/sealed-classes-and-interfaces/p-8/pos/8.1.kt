// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 8 -> sentence 8
 *                type-inference, smart-casts -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: more specific sealed subclasses should be matched before intermediate types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Node
sealed class Branch : Node()
data class Leaf(val v: Int) : Node()
data class Twig(val v: Int) : Branch()

fun case_1(n: Node) {
    checkSubtype<Int>(when (n) {
        is Leaf -> n.v
        is Twig -> n.v
        is Branch -> 0
    })
}
