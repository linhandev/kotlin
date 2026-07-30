// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 67 -> sentence 67
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 67 -> sentence 67
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 67 -> sentence 67
 * NUMBER: 1
 * DESCRIPTION: overload resolution selects invoke(Int) for Int argument
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Multi {
    operator fun invoke(x: Int): Int = x
    operator fun invoke(x: Number): Int = x.toInt() + 1000
}

fun case1() {
    checkSubtype<Int>(Multi()(42))
}
