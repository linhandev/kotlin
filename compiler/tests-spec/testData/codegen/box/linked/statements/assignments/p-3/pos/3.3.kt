// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 3 -> sentence 3
 * NUMBER: 3
 * DESCRIPTION: x?.linked[0] = null invokes set on non-null receiver branch
 */

var setCalls = 0

class Node

class Root

var Root?.linked: Node?
    get() = Node()
    set(v) {
        setCalls++
    }

operator fun Node?.get(i: Int): Node? = this

operator fun Node?.set(i: Int, v: Node?) {
    setCalls++
}

// TESTCASE NUMBER: 1
fun box(): String {
    setCalls = 0
    val x: Root? = Root()
    x?.linked[0] = null
    return if (setCalls == 1) "OK" else "NOK"
}
