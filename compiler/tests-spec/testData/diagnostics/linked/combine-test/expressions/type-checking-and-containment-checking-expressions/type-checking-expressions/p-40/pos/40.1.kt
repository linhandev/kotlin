// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 40 -> sentence 40
 *                type-system, subtyping, subtyping-rules -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: reified type parameter with upper bound A — type inference of is-check result is Boolean
 * HELPERS: checkType
 */

open class A
class B : A()

inline fun <reified T : A> checkIs(value: Any?): Boolean = value is T

// TESTCASE NUMBER: 1
fun case_1() {
    val b = B()
    checkSubtype<Boolean>(checkIs<A>(b))
    checkSubtype<Boolean>(checkIs<B>(b))
}
