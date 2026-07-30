// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 64 -> sentence 64
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 64 -> sentence 64
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 64 -> sentence 64
 * NUMBER: 1
 * DESCRIPTION: member invoke returning Nothing throws at runtime
 */

// TESTCASE NUMBER: 1
class Fail {
    operator fun invoke(): Nothing = throw Exception()
}

fun test(): Int = Fail()()

fun box(): String {
    try {
        test()
        return "NOK"
    } catch (_: Exception) {
        return "OK"
    }
}
