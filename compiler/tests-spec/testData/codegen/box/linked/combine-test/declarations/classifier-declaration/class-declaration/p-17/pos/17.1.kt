// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: nested class declares independent type parameter
 */

// TESTCASE NUMBER: 1
class Outer { class Nested<U>(val u: U) }

fun test(): String = Outer.Nested("x").u

fun box(): String {
    if (test() != "x") return "NOK"
    return "OK"
}
