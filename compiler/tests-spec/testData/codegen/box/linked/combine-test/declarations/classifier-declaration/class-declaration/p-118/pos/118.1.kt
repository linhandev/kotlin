// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 118 -> sentence 118
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 118 -> sentence 118
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 118 -> sentence 118
 * NUMBER: 1
 * DESCRIPTION: init block may read already-initialized properties in class declaration
 */

// TESTCASE NUMBER: 1
class Demo(val x: Int) {
    val y = x + 1

    init {
        check(y > 0)
    }
}

fun fromOne(): Int = Demo(1).y

fun fromFive(): Int = Demo(5).y

fun fromNine(): Int = Demo(9).y

fun box(): String {
    if (fromOne() != 2) return "NOK: one"
    if (fromFive() != 6) return "NOK: five"
    if (fromNine() != 10) return "NOK: nine"
    return "OK"
}
