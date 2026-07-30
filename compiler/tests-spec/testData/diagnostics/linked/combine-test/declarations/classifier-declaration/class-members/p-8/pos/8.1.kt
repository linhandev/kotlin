// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 8 -> sentence 8
 *                statements, assignments, operator-assignments -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: class member operator fun timesAssign in augmented assignment infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class MutableVector(var x: Int) {
    operator fun timesAssign(scalar: Int) { x *= scalar }
}

fun case1() {
    checkSubtype<Int>(MutableVector(2).also { it *= 3 }.x)
}
