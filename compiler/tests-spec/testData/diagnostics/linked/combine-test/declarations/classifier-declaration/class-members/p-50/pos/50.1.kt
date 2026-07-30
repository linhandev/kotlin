// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 50 -> sentence 50
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 50 -> sentence 50
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 50 -> sentence 50
 * NUMBER: 1
 * DESCRIPTION: data class invoke infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Data(val x: Int) {
    operator fun invoke(): Int = x * 2
}

fun case1() {
    checkSubtype<Int>(Data(42)())
}
