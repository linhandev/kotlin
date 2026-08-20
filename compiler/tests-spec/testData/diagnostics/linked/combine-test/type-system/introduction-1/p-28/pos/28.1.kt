// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: type-system, type-kinds, flexible-types, platform-types -> paragraph 28 -> sentence 28
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 28 -> sentence 28
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: reified is-check works on values originating from platform types type inference
 * HELPERS: checkType
 */

inline fun <reified T> isA56228(x: Any?): Boolean = x is T

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Boolean>(isA56228<String>(System.getProperty("user.name")))
}
