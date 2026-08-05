// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 22 -> sentence 22
 *                scopes-and-identifiers, identifiers-and-paths -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: FQCN reaches the other-package type when a same-named local class shadows the short name
 */
// FILE: a.kt
package pkg56022.lib

class Node56022(val id: Int = 1)

// FILE: box.kt
package pkg56022.app

class Node56022(val id: Int = 2)

// TESTCASE NUMBER: 1
fun testLib(): Int = pkg56022.lib.Node56022().id
fun testLocal(): Int = Node56022().id

fun box(): String {
    if (testLib() != 1) return "NOK"
    if (testLocal() != 2) return "NOK"
    return "OK"
}
