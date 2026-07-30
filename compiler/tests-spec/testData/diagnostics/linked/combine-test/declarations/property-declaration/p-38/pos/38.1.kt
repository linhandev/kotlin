// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 38 -> sentence 38
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: const val String property
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
const val name = "hello"

fun case_1() {
    checkSubtype<String>(name)
}
