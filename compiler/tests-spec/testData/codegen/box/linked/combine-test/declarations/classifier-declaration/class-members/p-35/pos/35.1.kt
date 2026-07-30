// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 35 -> sentence 35
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 35 -> sentence 35
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 35 -> sentence 35
 * NUMBER: 1
 * DESCRIPTION: no-arg member invoke returns Int constant
 */

// TESTCASE NUMBER: 1

class Generator {
    operator fun invoke() = 42
}

fun test(): Int = Generator()()

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
