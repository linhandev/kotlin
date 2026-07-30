// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: equals is false when primary constructor property values differ
 */

// TESTCASE NUMBER: 1
data class Key(val a: Int)

fun test(): Boolean = Key(1) == Key(2)

fun box(): String {
    if (test()) return "NOK"
    return "OK"
}
