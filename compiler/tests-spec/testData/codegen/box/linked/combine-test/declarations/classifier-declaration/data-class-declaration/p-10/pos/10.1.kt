// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: generated equals and hashCode compare primary constructor properties
 */

// TESTCASE NUMBER: 1
data class Key(val a: Int, val b: Int)

fun test(): Boolean =
    Key(1, 2) == Key(1, 2) && Key(1, 2).hashCode() == Key(1, 2).hashCode()

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
