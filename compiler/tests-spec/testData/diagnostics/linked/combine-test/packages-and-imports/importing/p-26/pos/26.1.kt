// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 26 -> sentence 26
 *                packages-and-imports, modules -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: file without package header belongs to the root package and can be imported by short name type inference
 * HELPERS: checkType
 */
// FILE: Root.kt
class RootSvc56026 {
    fun work56026(): Int = 1
}

// FILE: main.kt
package pkg56026.app

import RootSvc56026

import checkSubtype
// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<RootSvc56026>(RootSvc56026())
    checkSubtype<Int>(RootSvc56026().work56026())
}
