// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 43 -> sentence 43
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 43 -> sentence 43
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 43 -> sentence 43
 * NUMBER: 1
 * DESCRIPTION: invoke result matches declared String return type
 */

// TESTCASE NUMBER: 1
class Factory {
    operator fun invoke(): String = "hello"
}

fun test(): String = Factory()()

fun box(): String {
    if (test() != "hello") return "NOK"
    return "OK"
}
