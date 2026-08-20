// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 384 -> sentence 384
 * declarations, declaration-visibility -> paragraph 384 -> sentence 384
 * declarations, function-declaration -> paragraph 384 -> sentence 384
 * inheritance, inheriting -> paragraph 384 -> sentence 384
 * NUMBER: 1
 * DESCRIPTION: subclass can call parent public fun even if it uses private fun internally type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base { private fun token(): Int = 1; fun api(): Int = token() }

// TESTCASE NUMBER: 1
class Sub : Base() { fun test(): Int = api() }

// TESTCASE NUMBER: 1
fun test(): Int = Sub().test()

fun case1() {
    checkSubtype<Int>(test())
}
