// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: expressions, cast-expressions -> paragraph 12 -> sentence 12
 *                declarations, function-declaration -> paragraph 12 -> sentence 12
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: inline reified cast throws ClassCastException on type mismatch type inference
 * HELPERS: checkType
 */

inline fun <reified T> cast56212(x: Any): T = x as T

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<String>(cast56212<String>("s"))
}
