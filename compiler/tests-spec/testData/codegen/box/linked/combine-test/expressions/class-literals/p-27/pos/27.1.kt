// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 27 -> sentence 27
 *                inheritance, inheriting -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: KClass<Sub> can be assigned to KClass<Base> via covariance, verifying runtime semantics
 */

open class Base
class Sub: Base()

// TESTCASE NUMBER: 1
fun test(): kotlin.reflect.KClass<out Base> = Sub::class

fun box(): String {
    if (test() != Sub::class) return "NOK"
    return "OK"
}
