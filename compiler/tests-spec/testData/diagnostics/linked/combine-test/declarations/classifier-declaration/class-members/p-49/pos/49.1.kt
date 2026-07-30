// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 49 -> sentence 49
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 49 -> sentence 49
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 49 -> sentence 49
 * NUMBER: 1
 * DESCRIPTION: invoke reading property infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Container(var value: Int) {
    operator fun invoke(): Int = value
}

fun case1() {
    checkSubtype<Int>(Container(42)())
}
