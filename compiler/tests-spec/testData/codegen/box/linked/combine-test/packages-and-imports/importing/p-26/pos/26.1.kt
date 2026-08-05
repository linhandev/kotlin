// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 26 -> sentence 26
 *                packages-and-imports, modules -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: file without package header belongs to the root package and can be imported by short name
 */
// FILE: Root.kt
class RootSvc56026 {
    fun work56026(): Int = 1
}

// FILE: box.kt
package pkg56026.app

import RootSvc56026

// TESTCASE NUMBER: 1
fun test(): Int = RootSvc56026().work56026()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
