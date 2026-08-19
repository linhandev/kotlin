// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: generated toString includes property names and values
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class User(val name: String)

fun case_1() {
    checkSubtype<String>(User("Ann").toString())
}
