// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 98 -> sentence 98
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 98 -> sentence 98
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 98 -> sentence 98
 * NUMBER: 1
 * DESCRIPTION: multi-property data class equals when all match
 */

// TESTCASE NUMBER: 1
data class Data(val x: Int, val y: String)

fun test(): Boolean = Data(42, "a") == Data(42, "a")

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
