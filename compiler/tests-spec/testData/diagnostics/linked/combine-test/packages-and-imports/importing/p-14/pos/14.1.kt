// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 14 -> sentence 14
 *                declarations, classifier-declaration -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: nested class can be imported and constructed by short name type inference
 * HELPERS: checkType
 */
// FILE: a.kt
package pkg56014.api

class Outer56014 {
    class Inner56014
}

// FILE: main.kt
package pkg56014.app

import pkg56014.api.Outer56014.Inner56014

import checkSubtype
// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Inner56014>(Inner56014())
}
