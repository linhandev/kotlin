// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 17 -> sentence 17
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: enum entry can be imported across packages and used by short name
 */
// FILE: a.kt
package pkg56017.palette

enum class Color56017 { RED, GREEN }

// FILE: box.kt
package pkg56017.app

import pkg56017.palette.Color56017.RED

// TESTCASE NUMBER: 1
fun test(): pkg56017.palette.Color56017 = RED

fun box(): String {
    if (test() != pkg56017.palette.Color56017.RED) return "NOK"
    return "OK"
}
