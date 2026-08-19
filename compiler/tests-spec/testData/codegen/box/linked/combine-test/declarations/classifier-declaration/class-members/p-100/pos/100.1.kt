// LANGUAGE: +DataObjects
// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 100 -> sentence 100
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 100 -> sentence 100
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 100 -> sentence 100
 * NUMBER: 1
 * DESCRIPTION: data object not equal to unrelated class instance via Any
 */

// TESTCASE NUMBER: 1
data object MyObj
class Other

fun test(): Boolean = (MyObj as Any) == Other()

fun box(): String {
    if (test()) return "NOK"
    return "OK"
}
