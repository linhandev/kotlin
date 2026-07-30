// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 47 -> sentence 47
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 47 -> sentence 47
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 47 -> sentence 47
 * NUMBER: 1
 * DESCRIPTION: invoke delegating to lambda property infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Wrapper(val fn: () -> Int) {
    operator fun invoke(): Int = fn()
}

fun case1() {
    checkSubtype<Int>(Wrapper { 42 }())
}
