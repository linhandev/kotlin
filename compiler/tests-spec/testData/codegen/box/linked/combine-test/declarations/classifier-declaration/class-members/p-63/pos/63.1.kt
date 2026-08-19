// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 63 -> sentence 63
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 63 -> sentence 63
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 63 -> sentence 63
 *                expressions, when-expressions -> paragraph 63 -> sentence 63
 * NUMBER: 1
 * DESCRIPTION: when condition uses invoke; both branches covered
 */

// TESTCASE NUMBER: 1

class Callable(val x: Int) {
    operator fun invoke(): Int = x
}

fun test(c: Callable): String = when {
    c() > 0 -> "positive"
    else -> "non-positive"
}

fun box(): String {
    if (test(Callable(42)) != "positive") return "NOK"
    if (test(Callable(0)) != "non-positive") return "NOK"
    return "OK"
}
