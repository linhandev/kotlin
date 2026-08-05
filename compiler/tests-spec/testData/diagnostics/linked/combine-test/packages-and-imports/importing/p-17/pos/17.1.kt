// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 17 -> sentence 17
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: enum entry can be imported across packages and used by short name type inference
 * HELPERS: checkType
 */
// FILE: a.kt
package pkg56017.palette

enum class Color56017 { RED, GREEN }

// FILE: main.kt
package pkg56017.app

import pkg56017.palette.Color56017.RED

import checkSubtype
// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<pkg56017.palette.Color56017>(RED)
}
