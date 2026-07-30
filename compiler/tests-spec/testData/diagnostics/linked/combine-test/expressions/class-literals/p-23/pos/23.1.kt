// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -UPPER_BOUND_VIOLATED
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 23 -> sentence 23
 *                declarations, declarations-with-type-parameters -> paragraph 23 -> sentence 23
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: reified with nullable type argument String? infers KClass<String> (erased), verifying type inference
 * HELPERS: checkType
 */

inline fun <reified T> klass(): kotlin.reflect.KClass<T> = T::class

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<kotlin.reflect.KClass<String>>(klass<String>())
    checkSubtype<kotlin.reflect.KClass<Int>>(klass<Int>())
}
