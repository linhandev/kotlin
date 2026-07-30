/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 315 -> sentence 315
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 315 -> sentence 315
 * NUMBER: 1
 * DESCRIPTION: local class in a function is not a member nested classifier
 */

// TESTCASE NUMBER: 1
fun test(): Int {
    class Local(val v: Int)
    return Local(1).v
}

fun box(): String {
    if (test() != 1) return "NOK: test"
    fun other(): Int {
        class Local(val v: Int)
        return Local(2).v
    }
    if (other() != 2) return "NOK: other"
    return "OK"
}
