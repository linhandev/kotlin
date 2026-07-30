// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 54 -> sentence 54
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 54 -> sentence 54
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 54 -> sentence 54
 *                declarations, declarations-with-type-parameters -> paragraph 54 -> sentence 54
 * NUMBER: 1
 * DESCRIPTION: generic class member invoke returns type parameter value
 */

// TESTCASE NUMBER: 1
class Generic<T>(val value: T) {
    operator fun invoke(): T = value
}

fun test(): String = Generic("hello")()

fun box(): String {
    if (test() != "hello") return "NOK"
    return "OK"
}
