/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 312 -> sentence 312
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 312 -> sentence 312
 * NUMBER: 1
 * DESCRIPTION: this@Outer inside an inner class refers to the same outer instance
 */

// TESTCASE NUMBER: 1
class Outer {
    inner class Inner {
        fun ref(): Outer = this@Outer
    }

    fun same(): Boolean {
        val o = this
        return o.Inner().ref() === o
    }
}

fun test(): Boolean = Outer().same()

fun box(): String {
    if (!Outer().same()) return "NOK: same"
    if (!test()) return "NOK: test"
    val outer = Outer()
    if (outer.Inner().ref() !== outer) return "NOK: ref"
    return "OK"
}
