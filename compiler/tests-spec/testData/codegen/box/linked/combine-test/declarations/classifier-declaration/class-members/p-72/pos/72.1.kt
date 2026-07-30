// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 72 -> sentence 72
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 72 -> sentence 72
 * NUMBER: 1
 * DESCRIPTION: default equals is referential so distinct instances compare false
 */

// TESTCASE NUMBER: 1
class Box

fun test(): Boolean = Box() == Box()

fun box(): String {
    if (test()) return "NOK"
    return "OK"
}
