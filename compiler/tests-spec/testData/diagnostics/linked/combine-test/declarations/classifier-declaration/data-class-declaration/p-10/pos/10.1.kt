// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: generated equals and hashCode compare primary constructor properties
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Key(val a: Int, val b: Int)

fun case_1() {
    checkSubtype<Boolean>(Key(1, 2) == Key(1, 2))
}
