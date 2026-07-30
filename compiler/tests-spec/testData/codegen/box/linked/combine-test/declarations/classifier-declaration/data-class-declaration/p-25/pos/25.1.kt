// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: data object equality works without componentN
 */

// TESTCASE NUMBER: 1
data object Empty

fun test(): Boolean = Empty == Empty

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
