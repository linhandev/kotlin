// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 22 -> sentence 22
 *                declarations, declarations-with-type-parameters -> paragraph 22 -> sentence 22
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: reified type parameter T::class in inline function returns KClass<T> at call site, verifying runtime semantics
 */

inline fun <reified T : Any> klass(): kotlin.reflect.KClass<T> = T::class

// TESTCASE NUMBER: 1
fun test(): kotlin.reflect.KClass<Int> = klass<Int>()

fun box(): String {
    if (test() != Int::class) return "NOK"
    return "OK"
}
