// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 9 -> sentence 9
 *                declarations, declarations-with-type-parameters -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: raw List::class returns KClass<List<*>> without specific type arguments, verifying runtime semantics
 */

// TESTCASE NUMBER: 1
fun test(): kotlin.reflect.KClass<List<*>> = List::class

fun box(): String {
    if (test() != List::class) return "NOK"
    return "OK"
}
