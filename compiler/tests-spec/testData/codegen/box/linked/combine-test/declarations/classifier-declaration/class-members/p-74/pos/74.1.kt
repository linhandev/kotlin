// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 74 -> sentence 74
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 74 -> sentence 74
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 74 -> sentence 74
 * NUMBER: 1
 * DESCRIPTION: data class structural equals true for equal properties
 */

// TESTCASE NUMBER: 1
data class Data(val x: Int)

fun test(): Boolean = Data(42) == Data(42)

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
