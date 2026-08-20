// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 12 -> sentence 12
 *                declarations, declaration-visibility -> paragraph 12 -> sentence 12
 *                packages-and-imports, modules -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: public (default) top-level class can be imported from another package type inference
 * HELPERS: checkType
 */
// FILE: a.kt
package pkg56012.api

class Pub56012

// FILE: main.kt
package pkg56012.client

import pkg56012.api.Pub56012

import checkSubtype
// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Pub56012>(Pub56012())
}
