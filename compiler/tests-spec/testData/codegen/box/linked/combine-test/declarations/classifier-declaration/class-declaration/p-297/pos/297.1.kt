/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 297 -> sentence 297
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 297 -> sentence 297
 * NUMBER: 1
 * DESCRIPTION: this@Outer and inner this refer to different receivers
 */

// TESTCASE NUMBER: 1
class Outer {
    inner class Inner {
        fun same(): Boolean = (this@Outer as Any) !== (this as Any)
    }
}

fun test(): Boolean = Outer().Inner().same()

fun box(): String {
    if (!Outer().Inner().same()) return "NOK: same"
    if (!test()) return "NOK: test"
    val outer = Outer()
    if (outer.Inner().same() != ((outer as Any) !== (outer.Inner() as Any))) return "NOK: identity"
    return "OK"
}
