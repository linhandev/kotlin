// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 12 -> sentence 12
 *                declarations, declaration-visibility -> paragraph 12 -> sentence 12
 *                packages-and-imports, modules -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: public (default) top-level class can be imported from another package
 */
// FILE: a.kt
package pkg56012.api

class Pub56012(val n: Int = 3)

// FILE: box.kt
package pkg56012.client

import pkg56012.api.Pub56012

// TESTCASE NUMBER: 1
fun test(): Pub56012 = Pub56012()

fun box(): String {
    if (test().n != 3) return "NOK"
    return "OK"
}
