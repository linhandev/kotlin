// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 3 -> sentence 3
 *                packages-and-imports, modules -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: explicit import of a single type enables short-name construction across packages type inference
 * HELPERS: checkType
 */
// FILE: a.kt
package pkg56003.api

class Client56003

// FILE: main.kt
package pkg56003.app

import pkg56003.api.Client56003

import checkSubtype
// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Client56003>(Client56003())
}
