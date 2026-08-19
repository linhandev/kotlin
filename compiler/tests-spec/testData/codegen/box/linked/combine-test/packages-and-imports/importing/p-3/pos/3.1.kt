// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 3 -> sentence 3
 *                packages-and-imports, modules -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: explicit import of a single type enables short-name construction across packages
 */
// FILE: a.kt
package pkg56003.api

class Client56003(val id: Int = 7)

// FILE: box.kt
package pkg56003.app

import pkg56003.api.Client56003

// TESTCASE NUMBER: 1
fun test(): Client56003 = Client56003()

fun box(): String {
    if (test().id != 7) return "NOK"
    return "OK"
}
