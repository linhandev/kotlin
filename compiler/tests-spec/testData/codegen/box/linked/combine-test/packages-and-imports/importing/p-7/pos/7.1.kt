// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 7 -> sentence 7
 *                packages-and-imports, modules -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: star import brings top-level declarations from another package into short-name scope
 */
// FILE: a.kt
package pkg56007.tools

fun ping56007(): Int = 1

// FILE: box.kt
package pkg56007.app

import pkg56007.tools.*

// TESTCASE NUMBER: 1
fun test(): Int = ping56007()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
