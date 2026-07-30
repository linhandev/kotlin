/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 296 -> sentence 296
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 296 -> sentence 296
 * NUMBER: 1
 * DESCRIPTION: inner class implicitly holds a reference to the outer instance
 */

// TESTCASE NUMBER: 1
class Outer(val tag: String) {
    inner class Inner {
        fun outerTag(): String = this@Outer.tag
    }
}

fun test(): String = Outer("x").Inner().outerTag()

fun box(): String {
    val tag = Outer("x").Inner().outerTag()
    if (tag != "x") return "NOK: tag"
    if (test() != "x") return "NOK: test"
    if (Outer("ok").Inner().outerTag() != "ok") return "NOK: direct"
    return "OK"
}
