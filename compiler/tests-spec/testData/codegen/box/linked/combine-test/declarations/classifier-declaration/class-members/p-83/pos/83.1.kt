// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 83 -> sentence 83
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 83 -> sentence 83
 * NUMBER: 1
 * DESCRIPTION: non-null instance == null is false
 */

// TESTCASE NUMBER: 1

class Box

fun test(): Boolean = Box() == null

fun box(): String {
    if (test()) return "NOK"
    return "OK"
}
