// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 360 -> sentence 360
 * declarations, declaration-visibility -> paragraph 360 -> sentence 360
 * declarations, property-declaration -> paragraph 360 -> sentence 360
 * NUMBER: 1
 * DESCRIPTION: 成员函数可访问同类 private 属性 type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C { private val k = 10; private fun scale(x: Int): Int = x * k; fun test(): Int = scale(2) }

// TESTCASE NUMBER: 1
fun test(): Int = C().test()

fun case1() {
    checkSubtype<Int>(test())
}
