// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 70 -> sentence 70
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 70 -> sentence 70
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 70 -> sentence 70
 * NUMBER: 1
 * DESCRIPTION: pass member invoke as function reference to higher-order function
 */

// TESTCASE NUMBER: 1
class Callable {
    operator fun invoke(x: Int): Int = x * 2
}

fun transform(x: Int, fn: (Int) -> Int): Int = fn(x)

fun test(): Int = transform(42, Callable()::invoke)

fun box(): String {
    if (test() != 84) return "NOK"
    return "OK"
}
