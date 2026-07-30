// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 65 -> sentence 65
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 65 -> sentence 65
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 65 -> sentence 65
 *                expressions, elvis-operator-expressions -> paragraph 65 -> sentence 65
 * NUMBER: 1
 * DESCRIPTION: elvis after nullable invoke: null and non-null paths
 */

// TESTCASE NUMBER: 1

class Callable(val result: Int?) {
    operator fun invoke(): Int? = result
}

fun test(c: Callable): Int = c() ?: 0

fun box(): String {
    if (test(Callable(null)) != 0) return "NOK: null"
    if (test(Callable(42)) != 42) return "NOK: value"
    return "OK"
}
