// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 44 -> sentence 44
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 44 -> sentence 44
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 44 -> sentence 44
 * NUMBER: 1
 * DESCRIPTION: invoke(Int) overload resolution infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Multi {
    operator fun invoke(): Int = 1
    operator fun invoke(x: Int): Int = x
}

fun case1() {
    checkSubtype<Int>(Multi()(42))
}
