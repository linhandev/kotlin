// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 18 -> sentence 18
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: class member operator fun invoke call-form expression infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Calculator {
    operator fun invoke(a: Int, b: Int) = a + b
}

fun case1() {
    checkSubtype<Int>(Calculator()(1, 2))
}
