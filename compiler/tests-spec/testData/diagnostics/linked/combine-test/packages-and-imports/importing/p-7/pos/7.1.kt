// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 7 -> sentence 7
 *                packages-and-imports, modules -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: star import brings top-level declarations from another package into short-name scope type inference
 * HELPERS: checkType
 */
// FILE: a.kt
package pkg56007.tools

fun ping56007(): Int = 1

// FILE: main.kt
package pkg56007.app

import pkg56007.tools.*

import checkSubtype
// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Int>(ping56007())
}
