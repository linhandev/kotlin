// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: packages-and-imports, modules -> paragraph 23 -> sentence 23
 *                declarations, declaration-visibility -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: internal class with internal constructor is visible across packages in the same module type inference
 * HELPERS: checkType
 */
// FILE: a.kt
package pkg56023.api

internal class Cache56023 internal constructor()

// FILE: main.kt
package pkg56023.app

import pkg56023.api.Cache56023

import checkSubtype
// TESTCASE NUMBER: 1
fun case_1() {
    val c = Cache56023()
    checkSubtype<Cache56023>(c)
}
