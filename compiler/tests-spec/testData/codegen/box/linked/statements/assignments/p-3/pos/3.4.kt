// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 3 -> sentence 3
 * NUMBER: 4
 * DESCRIPTION: x?.linked[0] = null with null x completes without side effects
 */

class Node

class Root

var setterCalls = 0

var Root?.linked: Node?
    get() = Node()
    set(v) {
        setterCalls++
    }

operator fun Node?.get(i: Int): Node? = this

operator fun Node?.set(i: Int, v: Node?) {}

// TESTCASE NUMBER: 1
fun box(): String {
    setterCalls = 0
    val x: Root? = null
    x?.linked[0] = null
    return if (setterCalls == 0) "OK" else "NOK"
}
