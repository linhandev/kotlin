// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 378 -> sentence 378
 * declarations, declaration-visibility -> paragraph 378 -> sentence 378
 * declarations, function-declaration -> paragraph 378 -> sentence 378
 * NUMBER: 1
 * DESCRIPTION: 类内 private fun 可被同类成员调用 type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C { private fun secret(): Int = 1; fun get(): Int = secret() }

// TESTCASE NUMBER: 1
fun test(): Int = C().get()

fun case1() {
    checkSubtype<Int>(test())
}
