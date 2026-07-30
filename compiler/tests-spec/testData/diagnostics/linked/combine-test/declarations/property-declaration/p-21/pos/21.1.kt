// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: top-level val property
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
val globalX = 42

fun case_1() {
    checkSubtype<Int>(globalX)
}
