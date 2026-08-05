// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 4 -> sentence 4
 *                expressions, call-and-property-access-expressions, class-literals -> paragraph 4 -> sentence 4
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: reified is-check agrees with T::class for matching and rejecting values type inference
 * HELPERS: checkType
 */

inline fun <reified T : Any> matches56204(x: Any): Boolean = x is T && x::class == T::class

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Boolean>(matches56204<Int>(1))
    checkSubtype<Boolean>(matches56204<String>("s"))
}
