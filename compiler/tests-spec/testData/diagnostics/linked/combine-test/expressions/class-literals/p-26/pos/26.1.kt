// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 26 -> sentence 26
 *                declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: String::class infers KClass<String> assignable to KClass<out Any> via covariance, verifying type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val k: kotlin.reflect.KClass<out Any> = String::class
    checkSubtype<kotlin.reflect.KClass<out Any>>(k)
}
