// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 101 -> sentence 101
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 101 -> sentence 101
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 101 -> sentence 101
 * NUMBER: 1
 * DESCRIPTION: data class instance == null is false
 */

// TESTCASE NUMBER: 1

data class Data(val x: Int)

fun test(): Boolean = Data(42) == null

fun box(): String {
    if (test()) return "NOK"
    return "OK"
}
