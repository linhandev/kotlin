// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 58 -> sentence 58
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 58 -> sentence 58
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 58 -> sentence 58
 * NUMBER: 1
 * DESCRIPTION: constructor trailing lambda then invoke convention calls stored function
 */

// TESTCASE NUMBER: 1
class Callable(val fn: () -> Int) {
    operator fun invoke(): Int = fn()
}

fun test(): Int = Callable { 42 }()

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
