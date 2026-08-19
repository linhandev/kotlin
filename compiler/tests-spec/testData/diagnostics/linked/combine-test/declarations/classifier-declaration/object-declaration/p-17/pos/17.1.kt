// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 17 -> sentence 17
 *                inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: object can implement a sealed interface
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed interface Marker

object End : Marker

fun case_1() {
    checkSubtype<Marker>(End)
}
