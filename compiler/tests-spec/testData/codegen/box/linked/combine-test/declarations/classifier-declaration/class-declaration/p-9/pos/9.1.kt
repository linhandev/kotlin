// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: out variance allows OutBox Int as OutBox Number
 */

// TESTCASE NUMBER: 1
class OutBox<out T>(val value: T)

fun test(): OutBox<Number> = OutBox<Int>(1)

fun box(): String {
    if (test().value != 1) return "NOK"
    return "OK"
}
