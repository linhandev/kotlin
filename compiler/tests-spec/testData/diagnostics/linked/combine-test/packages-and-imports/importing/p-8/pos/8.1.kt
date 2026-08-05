// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 8 -> sentence 8
 *                declarations, classifier-declaration, object-declaration -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: star import introduces the top-level object type short name but members still require the type qualifier type inference
 * HELPERS: checkType
 */
// FILE: a.kt
package pkg56008.tools

object Config56008 {
    const val K = 1
}

// FILE: main.kt
package pkg56008.app

import pkg56008.tools.*

import checkSubtype
// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Int>(Config56008.K)
}
