// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 16 -> sentence 16
 *                declarations, function-declaration, extension-function-declaration -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: imported extension function can be called on a matching receiver type inference
 * HELPERS: checkType
 */
// FILE: a.kt
package pkg56016.ext

fun String.twice56016(): String = this + this

// FILE: main.kt
package pkg56016.app

import pkg56016.ext.twice56016

import checkSubtype
// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<String>("a".twice56016())
}
