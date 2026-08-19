// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 102 -> sentence 102
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 102 -> sentence 102
 * NUMBER: 1
 * DESCRIPTION: unrelated class instances compare false via Any ==
 */

// TESTCASE NUMBER: 1
class A
class B

fun test(): Boolean = (A() as Any) == B()

fun box(): String {
    if (test()) return "NOK"
    return "OK"
}
