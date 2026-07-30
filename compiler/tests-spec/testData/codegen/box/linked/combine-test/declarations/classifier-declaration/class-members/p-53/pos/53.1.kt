// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 53 -> sentence 53
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 53 -> sentence 53
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 53 -> sentence 53
 *                type-system, introduction-1 -> paragraph 53 -> sentence 53
 * NUMBER: 1
 * DESCRIPTION: safe call of member invoke on nullable receiver
 */

// TESTCASE NUMBER: 1
class Callable {
    operator fun invoke(): Int = 42
}

fun test(callable: Callable?): Int? = callable?.invoke()

fun box(): String {
    if (test(Callable()) != 42) return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
