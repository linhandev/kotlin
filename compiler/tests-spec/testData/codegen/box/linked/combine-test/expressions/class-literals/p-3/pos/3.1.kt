// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: instance ::class returns the actual runtime class, not the static type, verifying runtime semantics
 */

open class Base
class Sub: Base()

// TESTCASE NUMBER: 1
fun test(b: Base): kotlin.reflect.KClass<*> = b::class

fun box(): String {
    val sub = Sub()
    if (test(sub) != Sub::class) return "NOK"
    if (test(sub) == Base::class) return "NOK"
    return "OK"
}
