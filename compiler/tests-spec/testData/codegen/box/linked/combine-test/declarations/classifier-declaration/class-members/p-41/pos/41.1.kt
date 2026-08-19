// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 41 -> sentence 41
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 41 -> sentence 41
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 41 -> sentence 41
 * NUMBER: 1
 * DESCRIPTION: invoke result used in arithmetic expression
 */

// TESTCASE NUMBER: 1

class Callable {
    operator fun invoke(): Int = 40
}

fun test(): Int = Callable()() + 2

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
