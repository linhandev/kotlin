// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 58 -> sentence 58
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 58 -> sentence 58
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 58 -> sentence 58
 * NUMBER: 1
 * DESCRIPTION: constructor lambda plus invoke convention infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Callable(val fn: () -> Int) {
    operator fun invoke(): Int = fn()
}

fun case1() {
    checkSubtype<Int>(Callable { 42 }())
}
