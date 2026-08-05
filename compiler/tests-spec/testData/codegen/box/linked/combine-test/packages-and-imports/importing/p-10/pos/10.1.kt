// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 10 -> sentence 10
 *                packages-and-imports, modules -> paragraph 10 -> sentence 10
 *                declarations, declaration-visibility -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: internal top-level function can be imported and used across packages in the same module
 */
// FILE: a.kt
package pkg56010.moda

internal fun api56010(): Int = 1

// FILE: box.kt
package pkg56010.modb

import pkg56010.moda.api56010

// TESTCASE NUMBER: 1
fun test(): Int = api56010()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
