// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 41 -> sentence 41
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 41 -> sentence 41
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 41 -> sentence 41
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 41 -> sentence 41
 * NUMBER: 1
 * DESCRIPTION: reified is-check recognizes value class runtime type type inference
 * HELPERS: checkType
 */

@JvmInline
value class UserId56241(val raw: Int)

inline fun <reified T> isA56241(x: Any): Boolean = x is T

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Boolean>(isA56241<UserId56241>(UserId56241(1)))
}
