// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 48 -> sentence 48
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 48 -> sentence 48
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 48 -> sentence 48
 * NUMBER: 1
 * DESCRIPTION: invoke operator on anonymous object enables call convention
 */

// TESTCASE NUMBER: 1
fun test(): Int = object {
    operator fun invoke(): Int = 42
}()

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
