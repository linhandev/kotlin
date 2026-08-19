// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 9 -> sentence 9
 *                statements, assignments, operator-assignments -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: class member operator fun divAssign desugars to member call in augmented assignment
 */

// TESTCASE NUMBER: 1
class MutableVector(var x: Int) {
    operator fun divAssign(scalar: Int) { x /= scalar }
}

fun test(): Int = MutableVector(6).also { it /= 2 }.x

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
