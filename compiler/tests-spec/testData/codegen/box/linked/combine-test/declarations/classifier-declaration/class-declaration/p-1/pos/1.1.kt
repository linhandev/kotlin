// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: generic class constructor infers type argument
 */

// TESTCASE NUMBER: 1
class Box<T>(val value: T)

fun test(): Box<Int> = Box(1)

fun box(): String {
    if (test().value != 1) return "NOK"
    return "OK"
}
