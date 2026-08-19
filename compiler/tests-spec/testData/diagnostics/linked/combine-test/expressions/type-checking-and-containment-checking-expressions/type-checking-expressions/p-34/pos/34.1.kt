// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 34 -> sentence 34
 * PRIMARY LINKS: declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 34 -> sentence 34
 *                type-system, introduction-1 -> paragraph 34 -> sentence 34
 * NUMBER: 1
 * DESCRIPTION: reified type parameter T::class in inline function infers KClass<T> at call site, verifying type inference
 * HELPERS: checkType
 */

inline fun <reified T : Any> reifiedKlass(): kotlin.reflect.KClass<T> = T::class

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<kotlin.reflect.KClass<String>>(reifiedKlass<String>())
}
