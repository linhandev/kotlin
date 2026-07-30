// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 82 -> sentence 82
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 82 -> sentence 82
 *                expressions, equality-expressions, reference-equality-expressions -> paragraph 82 -> sentence 82
 * NUMBER: 1
 * DESCRIPTION: identical reference is referentially equal
 */

// TESTCASE NUMBER: 1
class Box

fun test(): Boolean = Box().let { it === it }

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
