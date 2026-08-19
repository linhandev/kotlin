// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: runtime class of Box Int equals Box String due to erasure
 */

// TESTCASE NUMBER: 1
class Box<T>

fun test(): Boolean = Box<Int>()::class == Box<String>()::class

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
