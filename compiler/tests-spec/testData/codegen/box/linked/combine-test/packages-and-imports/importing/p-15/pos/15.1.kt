// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 15 -> sentence 15
 *                declarations, classifier-declaration, object-declaration -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: companion object member function can be imported and called by short name
 */
// FILE: a.kt
package pkg56015.api

class Host56015 {
    companion object {
        fun ping56015(): Int = 1
    }
}

// FILE: box.kt
package pkg56015.app

import pkg56015.api.Host56015.Companion.ping56015

// TESTCASE NUMBER: 1
fun test(): Int = ping56015()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
