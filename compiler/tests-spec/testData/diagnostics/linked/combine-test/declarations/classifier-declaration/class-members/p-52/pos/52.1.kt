// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 52 -> sentence 52
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 52 -> sentence 52
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 52 -> sentence 52
 * NUMBER: 1
 * DESCRIPTION: interface invoke implementation infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Invokable {
    operator fun invoke(): Int
}

class Impl : Invokable {
    override operator fun invoke(): Int = 42
}

fun case1() {
    checkSubtype<Int>(Impl()())
}
