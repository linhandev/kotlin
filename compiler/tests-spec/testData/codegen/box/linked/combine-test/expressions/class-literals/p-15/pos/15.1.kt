// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: object declaration class literal Singleton::class returns KClass<Singleton>, verifying runtime semantics
 */

object Singleton

// TESTCASE NUMBER: 1
fun test(): kotlin.reflect.KClass<Singleton> = Singleton::class

fun box(): String {
    if (test() != Singleton::class) return "NOK"
    return "OK"
}
