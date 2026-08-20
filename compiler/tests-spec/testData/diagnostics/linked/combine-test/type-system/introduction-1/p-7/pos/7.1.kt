// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, class-literals -> paragraph 7 -> sentence 7
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 7 -> sentence 7
 *                type-system, type-kinds, nullable-types -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: reified T::class for T? still maps to the erased non-null class type inference
 * HELPERS: checkType
 */

inline fun <reified T> k56207(): kotlin.reflect.KClass<*> = T::class

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<kotlin.reflect.KClass<*>>(k56207<String?>())
}
