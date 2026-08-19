// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 56 -> sentence 56
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 56 -> sentence 56
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 56 -> sentence 56
 * NUMBER: 1
 * DESCRIPTION: invoke after apply configures receiver then calls operator
 */

// TESTCASE NUMBER: 1
class Builder(var value: Int) {
    operator fun invoke(): Int = value
}

fun test(): Int = Builder(0).apply { value = 42 }()

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
