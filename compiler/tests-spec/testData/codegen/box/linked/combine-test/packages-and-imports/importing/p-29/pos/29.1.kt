// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: packages-and-imports, modules -> paragraph 29 -> sentence 29
 *                packages-and-imports, importing -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: import only affects compile-time name resolution and does not change runtime module boundaries
 */
// FILE: a.kt
package pkg56029.api

fun version56029(): Int = 1

// FILE: box.kt
package pkg56029.app

import pkg56029.api.version56029

// TESTCASE NUMBER: 1
fun test(): Int = version56029()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
