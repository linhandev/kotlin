// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 42 -> sentence 42
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 42 -> sentence 42
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 42 -> sentence 42
 * NUMBER: 1
 * DESCRIPTION: two-argument invoke infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Adder {
    operator fun invoke(a: Int, b: Int): Int = a + b
}

fun case1() {
    checkSubtype<Int>(Adder()(1, 2))
}
