// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 84 -> sentence 84
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 84 -> sentence 84
 * NUMBER: 1
 * DESCRIPTION: class-typed nullable == for null/null and null/instance
 */

// TESTCASE NUMBER: 1

class Box

fun eq(a: Box?, b: Box?): Boolean = a == b

fun test(): Boolean = eq(null, null) && !eq(null, Box())

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
