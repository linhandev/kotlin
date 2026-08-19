// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 58 -> sentence 58
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 58 -> sentence 58
 *                type-inference, introduction-1 -> paragraph 58 -> sentence 58
 *                overload-resolution, choosing-the-most-specific-candidate-from-the-overload-candidate-set, algorithm-of-msc-selection -> paragraph 58 -> sentence 58
 * NUMBER: 1
 * DESCRIPTION: overload resolution selects non-generic overload over generic when more specific
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun f(x: Int): Int = x
fun <T> f(x: T): T = x

fun case_1() {
    checkSubtype<Int>(f(1))
    checkSubtype<String>(f("hello"))
}
