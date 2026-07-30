// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 9 -> sentence 9
 *                statements, assignments, operator-assignments -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: class member operator fun divAssign in augmented assignment infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class MutableVector(var x: Int) {
    operator fun divAssign(scalar: Int) { x /= scalar }
}

fun case1() {
    checkSubtype<Int>(MutableVector(6).also { it /= 2 }.x)
}
