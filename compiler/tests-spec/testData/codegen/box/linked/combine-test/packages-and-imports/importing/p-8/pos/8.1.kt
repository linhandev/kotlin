// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 8 -> sentence 8
 *                declarations, classifier-declaration, object-declaration -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: star import introduces the top-level object type short name but members still require the type qualifier
 */
// FILE: a.kt
package pkg56008.tools

object Config56008 {
    const val K = 1
}

// FILE: box.kt
package pkg56008.app

import pkg56008.tools.*

// TESTCASE NUMBER: 1
fun test(): Int = Config56008.K

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
