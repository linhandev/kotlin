// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 73 -> sentence 73
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 73 -> sentence 73
 * NUMBER: 1
 * DESCRIPTION: default equals is true for identical reference
 */

// TESTCASE NUMBER: 1
class Box

fun test(): Boolean = Box().let { it == it }

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
