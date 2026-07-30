// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: top-level var property
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
var globalX = 0

fun case_1() {
    checkSubtype<Int>(run { globalX = 42; globalX })
}
