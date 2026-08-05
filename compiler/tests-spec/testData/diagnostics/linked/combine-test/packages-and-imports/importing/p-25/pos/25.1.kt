// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 25 -> sentence 25
 *                declarations, type-alias -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: typealias can be imported and used as a type short name across packages type inference
 * HELPERS: checkType
 */
// FILE: a.kt
package pkg56025.api

typealias UserId56025 = Int

// FILE: main.kt
package pkg56025.app

import pkg56025.api.UserId56025

import checkSubtype
// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<UserId56025>(1)
}
