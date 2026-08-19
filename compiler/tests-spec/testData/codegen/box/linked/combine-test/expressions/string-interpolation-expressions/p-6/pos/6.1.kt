// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: property chain and call chain can be combined inside ${} interpolation
 */

// TESTCASE NUMBER: 1
data class Node(val next: Node?)

fun test(n: Node): String = "hash=${n.next?.hashCode()}"

fun box(): String {
    val leaf = Node(null)
    val root = Node(leaf)
    val withNext = test(root)
    if (!withNext.startsWith("hash=")) return "NOK"
    if (withNext == "hash=null") return "NOK"
    val withoutNext = test(leaf)
    if (withoutNext != "hash=null") return "NOK"
    return "OK"
}
