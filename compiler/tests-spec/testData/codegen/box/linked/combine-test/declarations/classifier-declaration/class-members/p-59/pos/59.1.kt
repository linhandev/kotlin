// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 59 -> sentence 59
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 59 -> sentence 59
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 59 -> sentence 59
 * NUMBER: 1
 * DESCRIPTION: inner class member invoke called on nested instance
 */

// TESTCASE NUMBER: 1
class Outer {
    inner class Inner {
        operator fun invoke(): String = "inner"
    }
}

fun test(): String = Outer().Inner()()

fun box(): String {
    if (test() != "inner") return "NOK"
    return "OK"
}
