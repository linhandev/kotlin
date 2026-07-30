// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 30 -> sentence 30
 *                inheritance, inheriting -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: callable reference Sub::f resolves to the overridden declaration in Sub and dispatches correctly, verifying runtime semantics
 */

open class Base { open fun f(): Int = 1 }
class Sub: Base() { override fun f(): Int = 2 }

val ref: (Sub) -> Int = Sub::f

// TESTCASE NUMBER: 1
fun test(s: Sub): Int = ref(s)

fun box(): String {
    if (test(Sub()) != 2) return "NOK"
    if (ref(Sub()) != 2) return "NOK"
    return "OK"
}
