// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 37 -> sentence 37
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 37 -> sentence 37
 * NUMBER: 1
 * DESCRIPTION: const val Float property
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
const val PI = 3.14f

fun case_1() {
    checkSubtype<Float>(PI)
}
