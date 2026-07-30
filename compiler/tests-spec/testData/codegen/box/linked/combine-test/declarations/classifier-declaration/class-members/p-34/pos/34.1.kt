// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 34 -> sentence 34
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 34 -> sentence 34
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 34 -> sentence 34
 * NUMBER: 1
 * DESCRIPTION: class member operator fun invoke with three arguments
 */

// TESTCASE NUMBER: 1
class Adder {
    operator fun invoke(a: Int, b: Int, c: Int) = a + b + c
}

fun test(): Int = Adder()(1, 2, 3)

fun box(): String {
    if (test() != 6) return "NOK"
    return "OK"
}
