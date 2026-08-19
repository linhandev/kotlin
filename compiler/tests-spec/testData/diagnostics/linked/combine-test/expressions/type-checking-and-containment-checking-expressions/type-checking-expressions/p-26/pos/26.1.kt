// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 26 -> sentence 26
 *                runtime-type-information, runtime-available-types -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: reified type parameter with non-nullable upper bound Number type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
inline fun <reified T : Number> isOfType(value: Any?): Boolean = value is T

fun case_1() {
    checkSubtype<Boolean>(isOfType<Int>(42))
    checkSubtype<Boolean>(isOfType<Int>("hello"))
}
