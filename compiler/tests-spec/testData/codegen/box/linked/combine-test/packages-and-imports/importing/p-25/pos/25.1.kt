// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 25 -> sentence 25
 *                declarations, type-alias -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: typealias can be imported and used as a type short name across packages
 */
// FILE: a.kt
package pkg56025.api

typealias UserId56025 = Int

// FILE: box.kt
package pkg56025.app

import pkg56025.api.UserId56025

// TESTCASE NUMBER: 1
fun test(): UserId56025 = 1

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
