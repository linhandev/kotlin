// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 1 -> sentence 1
 *                declarations, classifier-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: same-package top-level class is usable by short name without import type inference
 * HELPERS: checkType
 */
// FILE: service.kt
package pkg56001.app

class Service56001

// FILE: main.kt
package pkg56001.app

import checkSubtype
// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Service56001>(Service56001())
}
