// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: explicit type argument on constructor
 */

// TESTCASE NUMBER: 1
class Box<T>(val value: T)

fun test(): Box<Int> = Box<Int>(1)

fun box(): String {
    if (test().value != 1) return "NOK"
    return "OK"
}
