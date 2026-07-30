// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 115 -> sentence 115
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 115 -> sentence 115
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 115 -> sentence 115
 *                statements, code-blocks -> paragraph 115 -> sentence 115
 * NUMBER: 1
 * DESCRIPTION: secondary constructor body may execute arbitrary statements with side effects after this() delegation in class declaration
 */

// TESTCASE NUMBER: 1
class Holder(var v: Int) {
    constructor(x: Int, inc: Boolean) : this(x) {
        if (inc) v++
    }
}

fun viaIncrement(): Int = Holder(1, true).v

fun viaNoIncrement(): Int = Holder(1, false).v

fun viaPrimary(): Int = Holder(5).v

fun box(): String {
    if (viaIncrement() != 2) return "NOK: increment"
    if (viaNoIncrement() != 1) return "NOK: no increment"
    if (viaPrimary() != 5) return "NOK: primary"
    return "OK"
}
