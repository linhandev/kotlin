// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 121 -> sentence 121
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 121 -> sentence 121
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 121 -> sentence 121
 * NUMBER: 1
 * DESCRIPTION: init block may assign uninitialized val property exactly once in class declaration
 */

// TESTCASE NUMBER: 1
class Scaled(val seed: Int) {
    val out: Int

    init {
        out = seed * 2
    }
}

fun fromThree(): Int = Scaled(3).out

fun fromFive(): Int = Scaled(5).out

fun fromZero(): Int = Scaled(0).out

fun box(): String {
    if (fromThree() != 6) return "NOK: three"
    if (fromFive() != 10) return "NOK: five"
    if (fromZero() != 0) return "NOK: zero"
    return "OK"
}
