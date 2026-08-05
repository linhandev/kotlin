// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 10 -> sentence 10
 *                packages-and-imports, modules -> paragraph 10 -> sentence 10
 *                declarations, declaration-visibility -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: internal top-level function can be imported and used across packages in the same module type inference
 * HELPERS: checkType
 */
// FILE: a.kt
package pkg56010.moda

internal fun api56010(): Int = 1

// FILE: main.kt
package pkg56010.modb

import pkg56010.moda.api56010

import checkSubtype
// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Int>(api56010())
}
