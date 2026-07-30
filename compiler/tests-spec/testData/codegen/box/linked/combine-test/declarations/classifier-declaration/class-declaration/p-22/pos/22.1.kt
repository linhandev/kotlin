// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: generic data class structural equality
 */

// TESTCASE NUMBER: 1
data class Box<T>(val v: T)

fun test(): Boolean = Box(1) == Box(1)

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
