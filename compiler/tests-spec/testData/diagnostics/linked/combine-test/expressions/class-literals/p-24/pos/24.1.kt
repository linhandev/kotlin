// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 24 -> sentence 24
 *                declarations, declarations-with-type-parameters -> paragraph 24 -> sentence 24
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: reified type check isT<String?> with nullable type parameter infers Boolean, verifying type inference
 * HELPERS: checkType
 */

inline fun <reified T> isT(x: Any?): Boolean = x is T?

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Boolean>(isT<String?>(null))
    checkSubtype<Boolean>(isT<String?>("hello"))
}
