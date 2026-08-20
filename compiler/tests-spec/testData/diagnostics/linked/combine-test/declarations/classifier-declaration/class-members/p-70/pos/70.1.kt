// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 70 -> sentence 70
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 70 -> sentence 70
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 70 -> sentence 70
 * NUMBER: 1
 * DESCRIPTION: function reference to invoke(Int) accepted as (Int) -> Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Callable {
    operator fun invoke(x: Int): Int = x * 2
}

fun transform(x: Int, fn: (Int) -> Int): Int = fn(x)

fun case1() {
    checkSubtype<Int>(transform(42, Callable()::invoke))
}
