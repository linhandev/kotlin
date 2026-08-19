// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 23 -> sentence 23
 *                declarations, declarations-with-type-parameters -> paragraph 23 -> sentence 23
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: reified with nullable type argument String?::class is erased to KClass<String>, verifying runtime semantics
 */

inline fun <reified T : Any> klass(): kotlin.reflect.KClass<T> = T::class

// TESTCASE NUMBER: 1
fun test(): kotlin.reflect.KClass<String> = klass<String>()

fun box(): String {
    if (test() != String::class) return "NOK"
    return "OK"
}
