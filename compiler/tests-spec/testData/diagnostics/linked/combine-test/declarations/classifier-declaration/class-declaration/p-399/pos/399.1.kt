// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 399 -> sentence 399
 * declarations, declaration-visibility -> paragraph 399 -> sentence 399
 * declarations, function-declaration -> paragraph 399 -> sentence 399
 * NUMBER: 1
 * DESCRIPTION: 抽象类中 private fun 可在同类具体成员中调用 type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
abstract class A { private fun helper(): Int = 1; fun api(): Int = helper() }

// TESTCASE NUMBER: 1
class Impl : A()

// TESTCASE NUMBER: 1
fun test(): Int = Impl().api()

fun case1() {
    checkSubtype<Int>(test())
}
