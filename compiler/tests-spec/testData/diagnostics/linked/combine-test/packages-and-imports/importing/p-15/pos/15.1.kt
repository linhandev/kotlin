// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 15 -> sentence 15
 *                declarations, classifier-declaration, object-declaration -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: companion object member function can be imported and called by short name type inference
 * HELPERS: checkType
 */
// FILE: a.kt
package pkg56015.api

class Host56015 {
    companion object {
        fun ping56015(): Int = 1
    }
}

// FILE: main.kt
package pkg56015.app

import pkg56015.api.Host56015.Companion.ping56015

import checkSubtype
// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Int>(ping56015())
}
