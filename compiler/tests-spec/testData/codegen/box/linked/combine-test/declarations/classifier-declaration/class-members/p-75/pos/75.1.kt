// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 75 -> sentence 75
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 75 -> sentence 75
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 75 -> sentence 75
 * NUMBER: 1
 * DESCRIPTION: data class structural equals false for different properties
 */

// TESTCASE NUMBER: 1
data class Data(val x: Int)

fun test(): Boolean = Data(42) == Data(10)

fun box(): String {
    if (test()) return "NOK"
    return "OK"
}
