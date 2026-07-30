// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 50 -> sentence 50
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 50 -> sentence 50
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 50 -> sentence 50
 * NUMBER: 1
 * DESCRIPTION: data class member invoke uses primary constructor property
 */

// TESTCASE NUMBER: 1
data class Data(val x: Int) {
    operator fun invoke(): Int = x * 2
}

fun test(): Int = Data(42)()

fun box(): String {
    if (test() != 84) return "NOK"
    return "OK"
}
