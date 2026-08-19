// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, class-literals -> paragraph 7 -> sentence 7
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 7 -> sentence 7
 *                type-system, type-kinds, nullable-types -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: reified T::class for T? still maps to the erased non-null class
 */

// TESTCASE NUMBER: 1
inline fun <reified T> k56207(): kotlin.reflect.KClass<*> = T::class

fun box(): String {
    if (k56207<String?>() != String::class) return "NOK"
    if (k56207<Int?>() != Int::class) return "NOK"
    return "OK"
}
