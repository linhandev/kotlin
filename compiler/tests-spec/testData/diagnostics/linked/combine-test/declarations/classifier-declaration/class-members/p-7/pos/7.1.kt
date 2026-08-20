// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 7 -> sentence 7
 *                statements, assignments, operator-assignments -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: class member operator fun minusAssign in augmented assignment infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class MutableVector(var x: Int) {
    operator fun minusAssign(other: MutableVector) { x -= other.x }
}

fun case1() {
    checkSubtype<Int>(MutableVector(5).also { it -= MutableVector(2) }.x)
}
