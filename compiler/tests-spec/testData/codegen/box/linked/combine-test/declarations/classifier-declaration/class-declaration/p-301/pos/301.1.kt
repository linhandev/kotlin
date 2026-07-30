/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 301 -> sentence 301
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 301 -> sentence 301
 *                declarations, declaration-visibility -> paragraph 301 -> sentence 301
 * NUMBER: 1
 * DESCRIPTION: inner class can access private members of the outer instance
 */

// TESTCASE NUMBER: 1
class Outer(private val secret: Int) {
    inner class Inner {
        fun get(): Int = secret
    }
}

fun test(): Int = Outer(3).Inner().get()

fun box(): String {
    if (Outer(3).Inner().get() != 3) return "NOK: secret"
    if (test() != 3) return "NOK: test"
    if (Outer(9).Inner().get() != 9) return "NOK: direct"
    return "OK"
}
