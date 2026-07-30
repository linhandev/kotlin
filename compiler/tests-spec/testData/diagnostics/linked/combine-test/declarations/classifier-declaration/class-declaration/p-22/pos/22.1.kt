// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: generic data class structural equality
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Box<T>(val v: T)

fun test(): Boolean = Box(1) == Box(1)

fun case1() {
    checkSubtype<Boolean>(test())
}
