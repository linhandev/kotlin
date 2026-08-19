/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 306 -> sentence 306
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 306 -> sentence 306
 * NUMBER: 1
 * DESCRIPTION: outer class type parameter is visible inside an inner class
 */

// TESTCASE NUMBER: 1
class Outer<T>(val t: T) {
    inner class Inner {
        fun get(): T = t
    }
}

fun test(): Int = Outer(1).Inner().get()

fun box(): String {
    if (Outer(1).Inner().get() != 1) return "NOK: int"
    if (test() != 1) return "NOK: test"
    if (Outer("a").Inner().get() != "a") return "NOK: string"
    return "OK"
}
