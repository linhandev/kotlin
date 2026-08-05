// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 380 -> sentence 380
 * declarations, declaration-visibility -> paragraph 380 -> sentence 380
 * declarations, function-declaration -> paragraph 380 -> sentence 380
 * NUMBER: 1
 * DESCRIPTION: public fun 可封装暴露 private fun 结果 type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C { private fun compute(): Int = 2; fun api(): Int = compute() }

// TESTCASE NUMBER: 1
fun test(): Int = C().api()

fun case1() {
    checkSubtype<Int>(test())
}
