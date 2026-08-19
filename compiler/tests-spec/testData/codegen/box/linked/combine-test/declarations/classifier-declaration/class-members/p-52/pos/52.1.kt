// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 52 -> sentence 52
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 52 -> sentence 52
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 52 -> sentence 52
 * NUMBER: 1
 * DESCRIPTION: interface declare operator invoke implemented by class
 */

// TESTCASE NUMBER: 1
interface Invokable {
    operator fun invoke(): Int
}

class Impl : Invokable {
    override operator fun invoke(): Int = 42
}

fun test(): Int = Impl()()

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
