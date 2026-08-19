// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 95 -> sentence 95
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 95 -> sentence 95
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 95 -> sentence 95
 * NUMBER: 1
 * DESCRIPTION: data class equals true after copy with same properties
 */

// TESTCASE NUMBER: 1
data class Data(val x: Int)

fun test(): Boolean = Data(42) == Data(42).copy()

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
