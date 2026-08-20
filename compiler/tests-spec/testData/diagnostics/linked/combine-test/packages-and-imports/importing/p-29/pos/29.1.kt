// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: packages-and-imports, modules -> paragraph 29 -> sentence 29
 *                packages-and-imports, importing -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: import only affects compile-time name resolution and does not change runtime module boundaries type inference
 * HELPERS: checkType
 */
// FILE: a.kt
package pkg56029.api

fun version56029(): Int = 1

// FILE: main.kt
package pkg56029.app

import pkg56029.api.version56029

import checkSubtype
// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Int>(version56029())
}
