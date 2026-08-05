// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 399 -> sentence 399
 * declarations, declaration-visibility -> paragraph 399 -> sentence 399
 * declarations, function-declaration -> paragraph 399 -> sentence 399
 * NUMBER: 1
 * DESCRIPTION: 抽象类中 private fun 可在同类具体成员中调用
 */

// TESTCASE NUMBER: 1
abstract class A { private fun helper(): Int = 1; fun api(): Int = helper() }

// TESTCASE NUMBER: 1
class Impl : A()

// TESTCASE NUMBER: 1
fun test(): Int = Impl().api()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
