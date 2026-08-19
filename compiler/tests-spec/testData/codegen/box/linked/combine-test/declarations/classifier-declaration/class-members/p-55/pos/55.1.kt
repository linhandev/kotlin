// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 55 -> sentence 55
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 55 -> sentence 55
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 55 -> sentence 55
 * NUMBER: 1
 * DESCRIPTION: recursive member invoke computes factorial
 */

// TESTCASE NUMBER: 1
class Recursive {
    operator fun invoke(n: Int): Int = if (n <= 1) 1 else n * this(n - 1)
}

fun test(): Int = Recursive()(5)

fun box(): String {
    if (test() != 120) return "NOK"
    return "OK"
}
