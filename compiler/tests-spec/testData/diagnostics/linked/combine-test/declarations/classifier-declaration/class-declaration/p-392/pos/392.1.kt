// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 392 -> sentence 392
 * declarations, declaration-visibility -> paragraph 392 -> sentence 392
 * declarations, function-declaration -> paragraph 392 -> sentence 392
 * NUMBER: 1
 * DESCRIPTION: private fun 可在表达式体中定义 type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C { private fun double(x: Int): Int = x * 2; fun test(): Int = double(3) }

// TESTCASE NUMBER: 1
fun test(): Int = C().test()

fun case1() {
    checkSubtype<Int>(test())
}
