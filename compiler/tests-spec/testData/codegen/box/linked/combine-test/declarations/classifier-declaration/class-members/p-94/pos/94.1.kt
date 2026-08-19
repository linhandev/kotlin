// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 94 -> sentence 94
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 94 -> sentence 94
 * NUMBER: 1
 * DESCRIPTION: nullable param == null: true for null, false for instance
 */

// TESTCASE NUMBER: 1

class Box

fun test(b: Box?): Boolean = b == null

fun box(): String {
    if (!test(null)) return "NOK"
    if (test(Box())) return "NOK"
    return "OK"
}
