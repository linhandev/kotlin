// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 52 -> sentence 52
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 52 -> sentence 52
 *                type-inference, introduction-1 -> paragraph 52 -> sentence 52
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 52 -> sentence 52
 * NUMBER: 1
 * DESCRIPTION: reified type parameter supports runtime is-check at inline call site
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
inline fun <reified T> isA(x: Any): Boolean = x is T

fun case_1() {
    val result = isA<String>("hello")
    checkSubtype<Boolean>(result)
}
