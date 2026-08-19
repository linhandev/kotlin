// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 13 -> sentence 13
 *                type-inference, smart-casts -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: sealed interface when branch smart-casts to implementation type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed interface Node
data class Leaf(val v: Int) : Node

fun case_1(n: Node) {
    checkSubtype<Int>(when (n) {
        is Leaf -> n.v
    })
}
