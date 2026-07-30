// LANGUAGE: +DataObjects
// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 78 -> sentence 78
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 78 -> sentence 78
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 78 -> sentence 78
 * NUMBER: 1
 * DESCRIPTION: data object singleton equals itself
 */

// TESTCASE NUMBER: 1
data object MyObject

fun test(): Boolean = MyObject == MyObject

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
