// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 97 -> sentence 97
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 97 -> sentence 97
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 97 -> sentence 97
 * NUMBER: 1
 * DESCRIPTION: data class null property not equal to non-null; same non-null equal
 */

// TESTCASE NUMBER: 1

data class Data(val x: Int?)

fun test(): Boolean = Data(null) == Data(42)

fun box(): String {
    if (test()) return "NOK"
    if (!(Data(42) == Data(42))) return "NOK"
    return "OK"
}
