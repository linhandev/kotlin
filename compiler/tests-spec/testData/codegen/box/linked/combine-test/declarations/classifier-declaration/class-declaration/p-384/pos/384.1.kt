// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 384 -> sentence 384
 * declarations, declaration-visibility -> paragraph 384 -> sentence 384
 * declarations, function-declaration -> paragraph 384 -> sentence 384
 * inheritance, inheriting -> paragraph 384 -> sentence 384
 * NUMBER: 1
 * DESCRIPTION: 子类可调用父类 public fun 即使其内部使用 private fun
 */

// TESTCASE NUMBER: 1
open class Base { private fun token(): Int = 1; fun api(): Int = token() }

// TESTCASE NUMBER: 1
class Sub : Base() { fun test(): Int = api() }

// TESTCASE NUMBER: 1
fun test(): Int = Sub().test()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
