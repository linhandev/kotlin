// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: packages-and-imports, modules -> paragraph 23 -> sentence 23
 *                declarations, declaration-visibility -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: internal class with internal constructor is visible across packages in the same module
 */
// FILE: a.kt
package pkg56023.api

internal class Cache56023 internal constructor(val v: Int = 4)

// FILE: box.kt
package pkg56023.app

import pkg56023.api.Cache56023

// TESTCASE NUMBER: 1
fun test(): Int = Cache56023().v

fun box(): String {
    if (test() != 4) return "NOK"
    return "OK"
}
