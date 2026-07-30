// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: manually overridden equals still compiles for data class
 */

// TESTCASE NUMBER: 1
data class Wrap(val v: Int) {
    override fun equals(other: Any?): Boolean = other is Wrap && v == other.v
}

fun test(): Boolean = Wrap(1) == Wrap(1)

fun box(): String {
    if (!test()) return "NOK"
    if (Wrap(1) == Wrap(2)) return "NOK"
    return "OK"
}
